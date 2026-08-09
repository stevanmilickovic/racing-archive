package Racing.Archive.Backend.importing;

import Racing.Archive.Backend.domain.Driver;
import Racing.Archive.Backend.repository.DriverRepository;
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
public class DriverImportService {

    private static final List<String> REQUIRED_COLUMNS = List.of("first_name", "last_name", "nationality_code");

    private final DriverRepository driverRepository;

    @Transactional
    public DriverImportSummary importFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw badRequest("The CSV file is empty");
        }

        List<DriverImportRow> rows = parseRows(readRows(file));
        validateRows(rows);

        int createdDrivers = 0;
        int existingDrivers = 0;
        Map<String, Driver> processedDrivers = new HashMap<>();
        for (DriverImportRow row : rows) {
            Driver driver = processedDrivers.get(row.driverKey());
            if (driver == null) {
                List<Driver> matches = driverRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(row.firstName(), row.lastName());
                if (matches.isEmpty()) {
                    driver = new Driver();
                    driver.setFirstName(row.firstName());
                    driver.setLastName(row.lastName());
                    driver.setNationalityCode(row.nationalityCode());
                    driverRepository.save(driver);
                    createdDrivers++;
                } else {
                    driver = matches.getFirst();
                    if (driver.getNationalityCode() == null) {
                        driver.setNationalityCode(row.nationalityCode());
                    }
                    existingDrivers++;
                }
                processedDrivers.put(row.driverKey(), driver);
            }
        }

        return new DriverImportSummary(rows.size(), createdDrivers, existingDrivers);
    }

    private List<DriverImportRow> parseRows(List<Map<String, String>> csvRows) {
        List<DriverImportRow> rows = new ArrayList<>();
        List<ImportError> errors = new ArrayList<>();
        for (int index = 0; index < csvRows.size(); index++) {
            int rowNumber = index + 2;
            try {
                String firstName = required(csvRows.get(index), "first_name");
                String lastName = required(csvRows.get(index), "last_name");
                String nationalityCode = countryCode(required(csvRows.get(index), "nationality_code"));
                rows.add(new DriverImportRow(rowNumber, firstName, lastName, nationalityCode));
            } catch (IllegalArgumentException exception) {
                errors.add(new ImportError(rowNumber, driverName(csvRows.get(index)), exception.getMessage()));
            }
        }
        if (!errors.isEmpty()) {
            throw new ImportValidationException(errors);
        }
        return rows;
    }

    private void validateRows(List<DriverImportRow> rows) {
        List<ImportError> errors = new ArrayList<>();
        Map<String, DriverImportRow> rowsByName = new HashMap<>();
        Map<String, List<Driver>> driversByName = new HashMap<>();

        for (DriverImportRow row : rows) {
            DriverImportRow previous = rowsByName.putIfAbsent(row.driverKey(), row);
            if (previous != null && !previous.nationalityCode().equals(row.nationalityCode())) {
                errors.add(new ImportError(row.rowNumber(), row.driverName(), "The CSV contains this name with a different nationality"));
                continue;
            }

            List<Driver> matches = driversByName.computeIfAbsent(row.driverKey(), ignored ->
                    driverRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(row.firstName(), row.lastName()));
            if (matches.size() > 1) {
                errors.add(new ImportError(row.rowNumber(), row.driverName(), "Multiple existing drivers have this name; resolve the duplicate records before importing"));
            } else if (matches.size() == 1 && matches.getFirst().getNationalityCode() != null
                    && !matches.getFirst().getNationalityCode().equalsIgnoreCase(row.nationalityCode())) {
                errors.add(new ImportError(row.rowNumber(), row.driverName(), "An existing driver with this name has nationality " + matches.getFirst().getNationalityCode()));
            }
        }

        if (!errors.isEmpty()) {
            throw new ImportValidationException(errors);
        }
    }

    private List<Map<String, String>> readRows(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String header = reader.readLine();
            if (header == null) throw badRequest("The CSV file is empty");
            List<String> headers = parseCsvLine(removeBom(header));
            if (!headers.equals(REQUIRED_COLUMNS)) throw badRequest("CSV headers must exactly match the drivers template");

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
        String value = row.get(name);
        if (value == null || value.isBlank()) throw new IllegalArgumentException(name + " is required");
        return value.trim();
    }

    private String countryCode(String value) {
        String normalized = value.toUpperCase(Locale.ROOT);
        if (normalized.length() != 2) throw new IllegalArgumentException("nationality_code must be a two-letter country code");
        return normalized;
    }

    private String driverName(Map<String, String> row) { return row.getOrDefault("first_name", "Unknown") + " " + row.getOrDefault("last_name", "driver"); }
    private String removeBom(String value) { return value.startsWith("\uFEFF") ? value.substring(1) : value; }
    private ResponseStatusException badRequest(String message) { return new ResponseStatusException(HttpStatus.BAD_REQUEST, message); }

    private record DriverImportRow(int rowNumber, String firstName, String lastName, String nationalityCode) {
        String driverName() { return firstName + " " + lastName; }
        String driverKey() { return firstName.toLowerCase(Locale.ROOT) + "|" + lastName.toLowerCase(Locale.ROOT); }
    }
}
