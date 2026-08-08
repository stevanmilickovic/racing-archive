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
            "driver_first_name", "driver_last_name", "driver_nationality_code", "club_name", "club_country_code",
            "vehicle_manufacturer", "vehicle_model", "vehicle_display_name", "class_code", "race_number",
            "elapsed_time_millis", "position", "result_status");

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
        List<Map<String, String>> rows = readRows(file);
        ImportCounts counts = new ImportCounts();

        for (int index = 0; index < rows.size(); index++) {
            importRow(session, rows.get(index), index + 2, counts);
        }

        return new SessionResultImportSummary(rows.size(), counts.createdDrivers, counts.createdClubs,
                counts.createdVehicles, counts.createdEntries, counts.updatedSessionResults);
    }

    private void importRow(RaceSession session, Map<String, String> row, int rowNumber, ImportCounts counts) {
        String firstName = required(row, "driver_first_name", rowNumber);
        String lastName = required(row, "driver_last_name", rowNumber);
        String nationalityCode = countryCode(required(row, "driver_nationality_code", rowNumber), "driver_nationality_code", rowNumber);
        String clubName = required(row, "club_name", rowNumber);
        String clubCountryCode = countryCode(required(row, "club_country_code", rowNumber), "club_country_code", rowNumber);
        String manufacturer = required(row, "vehicle_manufacturer", rowNumber);
        String model = required(row, "vehicle_model", rowNumber);
        String displayName = optional(row, "vehicle_display_name");
        String classCode = required(row, "class_code", rowNumber).toUpperCase(Locale.ROOT);

        CompetitionClass competitionClass = competitionClassRepository
                .findBySeasonIdAndCodeIgnoreCase(session.getRound().getSeason().getId(), classCode)
                .orElseThrow(() -> badRequest("Row " + rowNumber + ": class '" + classCode + "' does not exist in this session's season"));
        Driver driver = findOrCreateDriver(firstName, lastName, nationalityCode, counts);
        Club club = findOrCreateClub(clubName, clubCountryCode, counts);
        Vehicle vehicle = findOrCreateVehicle(manufacturer, model, displayName, counts);
        Entry entry = findOrCreateEntry(session, driver, club, vehicle, competitionClass, row, rowNumber, counts);

        SessionResult result = sessionResultRepository.findBySessionIdAndEntryId(session.getId(), entry.getId())
                .orElseGet(SessionResult::new);
        boolean existing = result.getId() != null;
        result.setSession(session);
        result.setEntry(entry);
        result.setElapsedTimeMillis(longValue(optional(row, "elapsed_time_millis"), "elapsed_time_millis", rowNumber));
        result.setPosition(integerValue(optional(row, "position"), "position", rowNumber));
        result.setStatus(enumValue(required(row, "result_status", rowNumber), ResultStatus.class, "result_status", rowNumber));
        sessionResultRepository.save(result);
        if (existing) {
            counts.updatedSessionResults++;
        }
    }

    private Driver findOrCreateDriver(String firstName, String lastName, String nationalityCode, ImportCounts counts) {
        return driverRepository.findFirstByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndNationalityCode(firstName, lastName, nationalityCode)
                .orElseGet(() -> {
                    Driver driver = new Driver();
                    driver.setFirstName(firstName);
                    driver.setLastName(lastName);
                    driver.setNationalityCode(nationalityCode);
                    counts.createdDrivers++;
                    return driverRepository.save(driver);
                });
    }

    private Club findOrCreateClub(String name, String countryCode, ImportCounts counts) {
        return clubRepository.findFirstByNameIgnoreCaseAndCountryCode(name, countryCode)
                .orElseGet(() -> {
                    Club club = new Club();
                    club.setName(name);
                    club.setCountryCode(countryCode);
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
            Map<String, String> row, int rowNumber, ImportCounts counts) {
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
        entry.setRaceNumber(integerValue(optional(row, "race_number"), "race_number", rowNumber));
        return entryRepository.save(entry);
    }

    private List<Map<String, String>> readRows(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String header = reader.readLine();
            if (header == null) {
                throw badRequest("The CSV file is empty");
            }
            List<String> headers = parseCsvLine(removeBom(header));
            validateHeaders(headers);
            List<Map<String, String>> rows = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                List<String> values = parseCsvLine(line);
                if (values.size() != headers.size()) {
                    throw badRequest("CSV row " + (rows.size() + 2) + " has " + values.size() + " values; expected " + headers.size());
                }
                Map<String, String> row = new HashMap<>();
                for (int index = 0; index < headers.size(); index++) {
                    row.put(headers.get(index), values.get(index).trim());
                }
                rows.add(row);
            }
            return rows;
        } catch (IOException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to read the CSV file", exception);
        }
    }

    private void validateHeaders(List<String> headers) {
        if (!headers.equals(REQUIRED_COLUMNS)) {
            throw badRequest("CSV headers must exactly match the session-results template");
        }
    }

    private List<String> parseCsvLine(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder value = new StringBuilder();
        boolean quoted = false;
        for (int index = 0; index < line.length(); index++) {
            char character = line.charAt(index);
            if (character == '"') {
                if (quoted && index + 1 < line.length() && line.charAt(index + 1) == '"') {
                    value.append(character);
                    index++;
                } else {
                    quoted = !quoted;
                }
            } else if (character == ',' && !quoted) {
                values.add(value.toString());
                value.setLength(0);
            } else {
                value.append(character);
            }
        }
        if (quoted) {
            throw badRequest("CSV contains an unclosed quoted value");
        }
        values.add(value.toString());
        return values;
    }

    private String required(Map<String, String> row, String name, int rowNumber) {
        String value = optional(row, name);
        if (value == null) {
            throw badRequest("Row " + rowNumber + ": " + name + " is required");
        }
        return value;
    }

    private String optional(Map<String, String> row, String name) {
        String value = row.get(name);
        return value == null || value.isBlank() ? null : value.trim();
    }

    private String countryCode(String value, String name, int rowNumber) {
        String normalized = value.toUpperCase(Locale.ROOT);
        if (normalized.length() != 2) {
            throw badRequest("Row " + rowNumber + ": " + name + " must be a two-letter country code");
        }
        return normalized;
    }

    private Long longValue(String value, String name, int rowNumber) {
        if (value == null) return null;
        try { return Long.valueOf(value); } catch (NumberFormatException exception) { throw badRequest("Row " + rowNumber + ": " + name + " must be a whole number"); }
    }

    private Integer integerValue(String value, String name, int rowNumber) {
        if (value == null) return null;
        try { return Integer.valueOf(value); } catch (NumberFormatException exception) { throw badRequest("Row " + rowNumber + ": " + name + " must be a whole number"); }
    }

    private <T extends Enum<T>> T enumValue(String value, Class<T> type, String name, int rowNumber) {
        try { return Enum.valueOf(type, value.toUpperCase(Locale.ROOT)); } catch (IllegalArgumentException exception) { throw badRequest("Row " + rowNumber + ": " + name + " is invalid"); }
    }

    private String removeBom(String value) { return value.startsWith("\uFEFF") ? value.substring(1) : value; }
    private ResponseStatusException badRequest(String message) { return new ResponseStatusException(HttpStatus.BAD_REQUEST, message); }

    private static class ImportCounts {
        private int createdDrivers;
        private int createdClubs;
        private int createdVehicles;
        private int createdEntries;
        private int updatedSessionResults;
    }
}
