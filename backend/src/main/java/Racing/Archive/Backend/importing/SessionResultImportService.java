package Racing.Archive.Backend.importing;

import Racing.Archive.Backend.domain.Club;
import Racing.Archive.Backend.domain.CompetitionClass;
import Racing.Archive.Backend.domain.Driver;
import Racing.Archive.Backend.domain.Entry;
import Racing.Archive.Backend.domain.EntryStatus;
import Racing.Archive.Backend.domain.RaceSession;
import Racing.Archive.Backend.domain.ResultStatus;
import Racing.Archive.Backend.domain.SessionResult;
import Racing.Archive.Backend.domain.Vehicle;
import Racing.Archive.Backend.repository.ClubRepository;
import Racing.Archive.Backend.repository.CompetitionClassRepository;
import Racing.Archive.Backend.repository.DriverRepository;
import Racing.Archive.Backend.repository.EntryRepository;
import Racing.Archive.Backend.repository.RaceSessionRepository;
import Racing.Archive.Backend.repository.SessionResultRepository;
import Racing.Archive.Backend.repository.VehicleRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class SessionResultImportService {

    private static final List<String> REQUIRED_COLUMNS = List.of(
            "driver_first_name", "driver_last_name", "club_name", "vehicle_manufacturer", "vehicle_model",
            "vehicle_display_name", "class_code", "race_number", "elapsed_time_millis", "position", "result_status");

    private final RaceSessionRepository raceSessionRepository;
    private final CompetitionClassRepository competitionClassRepository;
    private final DriverRepository driverRepository;
    private final ClubRepository clubRepository;
    private final VehicleRepository vehicleRepository;
    private final EntryRepository entryRepository;
    private final SessionResultRepository sessionResultRepository;

    @Transactional
    public SessionResultImportSummary importFile(Long sessionId, MultipartFile file) {
        if (file.isEmpty()) {
            throw badRequest("The CSV file is empty");
        }

        RaceSession session = raceSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Race session " + sessionId + " was not found"));
        List<ImportRow> rows = parseRows(readRows(file));
        validateRows(rows);

        ImportCounts counts = new ImportCounts();
        Map<String, Driver> drivers = new HashMap<>();
        for (ImportRow row : rows) {
            importRow(session, row, drivers, counts);
        }

        return new SessionResultImportSummary(rows.size(), counts.createdDrivers, counts.createdClubs,
                counts.createdVehicles, counts.createdEntries, counts.updatedSessionResults);
    }

    private List<ImportRow> parseRows(List<Map<String, String>> csvRows) {
        List<ImportRow> rows = new ArrayList<>();
        List<ImportError> errors = new ArrayList<>();
        for (int index = 0; index < csvRows.size(); index++) {
            int rowNumber = index + 2;
            try {
                rows.add(toImportRow(csvRows.get(index), rowNumber));
            } catch (IllegalArgumentException exception) {
                errors.add(new ImportError(rowNumber, driverName(csvRows.get(index)), exception.getMessage()));
            }
        }
        if (!errors.isEmpty()) {
            throw new ImportValidationException(errors);
        }
        return rows;
    }

    private ImportRow toImportRow(Map<String, String> row, int rowNumber) {
        return new ImportRow(
                rowNumber,
                required(row, "driver_first_name"),
                required(row, "driver_last_name"),
                required(row, "club_name"),
                required(row, "vehicle_manufacturer"),
                required(row, "vehicle_model"),
                optional(row, "vehicle_display_name"),
                required(row, "class_code").toUpperCase(Locale.ROOT),
                integerValue(optional(row, "race_number"), "race_number"),
                longValue(optional(row, "elapsed_time_millis"), "elapsed_time_millis"),
                integerValue(optional(row, "position"), "position"),
                enumValue(required(row, "result_status"), ResultStatus.class, "result_status"));
    }

    private void validateRows(List<ImportRow> rows) {
        List<ImportError> errors = new ArrayList<>();
        Map<String, List<Driver>> driversByName = new HashMap<>();
        for (ImportRow row : rows) {
            if (competitionClassRepository.findByCodeIgnoreCase(row.classCode()).isEmpty()) {
                errors.add(new ImportError(row.rowNumber(), row.driverName(), "Competition class '" + row.classCode() + "' does not exist"));
            }
            List<Driver> matches = driversByName.computeIfAbsent(row.driverKey(), ignored ->
                    driverRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(row.firstName(), row.lastName()));
            if (matches.size() > 1) {
                errors.add(new ImportError(row.rowNumber(), row.driverName(), "Multiple existing drivers have this name; resolve the duplicate driver records before importing"));
            }
        }
        if (!errors.isEmpty()) {
            throw new ImportValidationException(errors);
        }
    }

    private void importRow(RaceSession session, ImportRow row, Map<String, Driver> drivers, ImportCounts counts) {
        CompetitionClass competitionClass = competitionClassRepository.findByCodeIgnoreCase(row.classCode()).orElseThrow();
        Driver driver = drivers.computeIfAbsent(row.driverKey(), ignored -> findOrCreateDriver(row, counts));
        Club club = findOrCreateClub(row.clubName(), counts);
        Vehicle vehicle = findOrCreateVehicle(row.manufacturer(), row.model(), row.displayName(), counts);
        Entry entry = findOrCreateEntry(session, driver, club, vehicle, competitionClass, row, counts);

        SessionResult result = sessionResultRepository.findBySessionIdAndEntryId(session.getId(), entry.getId())
                .orElseGet(SessionResult::new);
        boolean existing = result.getId() != null;
        result.setSession(session);
        result.setEntry(entry);
        result.setElapsedTimeMillis(row.elapsedTimeMillis());
        result.setPosition(row.position());
        result.setStatus(row.resultStatus());
        sessionResultRepository.save(result);
        if (existing) {
            counts.updatedSessionResults++;
        }
    }

    private Driver findOrCreateDriver(ImportRow row, ImportCounts counts) {
        List<Driver> matches = driverRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(row.firstName(), row.lastName());
        if (!matches.isEmpty()) {
            return matches.getFirst();
        }
        Driver driver = new Driver();
        driver.setFirstName(row.firstName());
        driver.setLastName(row.lastName());
        counts.createdDrivers++;
        return driverRepository.save(driver);
    }

    private Club findOrCreateClub(String name, ImportCounts counts) {
        return clubRepository.findFirstByNameIgnoreCase(name)
                .orElseGet(() -> {
                    Club club = new Club();
                    club.setName(name);
                    counts.createdClubs++;
                    return clubRepository.save(club);
                });
    }

    private Vehicle findOrCreateVehicle(String manufacturer, String model, String displayName, ImportCounts counts) {
        return vehicleRepository.findFirstByManufacturerIgnoreCaseAndModelIgnoreCaseAndDisplayName(manufacturer, model, displayName)
                .orElseGet(() -> {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setManufacturer(manufacturer);
                    vehicle.setModel(model);
                    vehicle.setDisplayName(displayName);
                    counts.createdVehicles++;
                    return vehicleRepository.save(vehicle);
                });
    }

    private Entry findOrCreateEntry(
            RaceSession session, Driver driver, Club club, Vehicle vehicle, CompetitionClass competitionClass,
            ImportRow row, ImportCounts counts) {
        Entry entry = entryRepository.findByRoundIdAndDriverId(session.getRound().getId(), driver.getId())
                .orElseGet(() -> {
                    Entry created = new Entry();
                    created.setRound(session.getRound());
                    created.setDriver(driver);
                    created.setStatus(EntryStatus.ENTERED);
                    counts.createdEntries++;
                    return created;
                });
        entry.setClub(club);
        entry.setVehicle(vehicle);
        entry.setCompetitionClass(competitionClass);
        entry.setRaceNumber(row.raceNumber());
        return entryRepository.save(entry);
    }

    private List<Map<String, String>> readRows(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String header = reader.readLine();
            if (header == null) throw badRequest("The CSV file is empty");
            List<String> headers = parseCsvLine(removeBom(header));
            if (!headers.equals(REQUIRED_COLUMNS)) throw badRequest("CSV headers must exactly match the session-results template");

            List<Map<String, String>> rows = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                List<String> values = parseCsvLine(line);
                if (values.size() != headers.size()) throw badRequest("CSV row " + (rows.size() + 2) + " has an incorrect number of values");
                Map<String, String> row = new HashMap<>();
                for (int index = 0; index < headers.size(); index++) row.put(headers.get(index), values.get(index).trim());
                rows.add(row);
            }
            return rows;
        } catch (IOException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to read the CSV file", exception);
        }
    }

    private List<String> parseCsvLine(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder value = new StringBuilder();
        boolean quoted = false;
        for (int index = 0; index < line.length(); index++) {
            char character = line.charAt(index);
            if (character == '"') {
                if (quoted && index + 1 < line.length() && line.charAt(index + 1) == '"') { value.append(character); index++; }
                else quoted = !quoted;
            } else if (character == ',' && !quoted) { values.add(value.toString()); value.setLength(0); }
            else value.append(character);
        }
        if (quoted) throw badRequest("CSV contains an unclosed quoted value");
        values.add(value.toString());
        return values;
    }

    private String required(Map<String, String> row, String name) {
        String value = optional(row, name);
        if (value == null) throw new IllegalArgumentException(name + " is required");
        return value;
    }

    private String optional(Map<String, String> row, String name) {
        String value = row.get(name);
        return value == null || value.isBlank() ? null : value.trim();
    }

    private Long longValue(String value, String name) {
        if (value == null) return null;
        try { return Long.valueOf(value); } catch (NumberFormatException exception) { throw new IllegalArgumentException(name + " must be a whole number"); }
    }

    private Integer integerValue(String value, String name) {
        if (value == null) return null;
        try { return Integer.valueOf(value); } catch (NumberFormatException exception) { throw new IllegalArgumentException(name + " must be a whole number"); }
    }

    private <T extends Enum<T>> T enumValue(String value, Class<T> type, String name) {
        try { return Enum.valueOf(type, value.toUpperCase(Locale.ROOT)); }
        catch (IllegalArgumentException exception) { throw new IllegalArgumentException(name + " is invalid"); }
    }

    private String driverName(Map<String, String> row) { return String.join(" ", optional(row, "driver_first_name") == null ? "Unknown" : optional(row, "driver_first_name"), optional(row, "driver_last_name") == null ? "driver" : optional(row, "driver_last_name")); }
    private String removeBom(String value) { return value.startsWith("\uFEFF") ? value.substring(1) : value; }
    private ResponseStatusException badRequest(String message) { return new ResponseStatusException(HttpStatus.BAD_REQUEST, message); }

    private record ImportRow(int rowNumber, String firstName, String lastName, String clubName, String manufacturer,
                             String model, String displayName, String classCode, Integer raceNumber,
                             Long elapsedTimeMillis, Integer position, ResultStatus resultStatus) {
        String driverName() { return firstName + " " + lastName; }
        String driverKey() { return firstName.toLowerCase(Locale.ROOT) + "|" + lastName.toLowerCase(Locale.ROOT); }
    }

    private static class ImportCounts {
        private int createdDrivers;
        private int createdClubs;
        private int createdVehicles;
        private int createdEntries;
        private int updatedSessionResults;
    }
}
