package Racing.Archive.Backend.importing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PdfConversionService {

    private static final String DRIVER_HEADER = "first_name,last_name,nationality_code";
    private static final String RESULT_HEADER = "driver_first_name,driver_last_name,club_name,vehicle_manufacturer,vehicle_model,vehicle_display_name,class_code,race_number,run_1_elapsed_time_millis,run_1_status,run_2_elapsed_time_millis,run_2_status,elapsed_time_millis,position,result_status";
    private static final Pattern FINISHED_ENTRY = Pattern.compile("^\\d+\\s+\\d+\\s+[12]$");
    private static final Pattern NON_FINISHER_ENTRY = Pattern.compile("^\\d+\\s+[12]$");
    private static final Pattern TIME = Pattern.compile("(\\d+):(\\d{2})\\.(\\d{3})");
    private static final Pattern CLUB_AND_RESULT = Pattern.compile("^(.*?)(?:\\s+(?:\\d+:\\d{2}\\.\\d{3}|DNS|DNF|DISQ)).*$", Pattern.CASE_INSENSITIVE);
    private static final Map<String, String> NATIONALITY_CODES = Map.of(
            "MNE", "ME", "SRB", "RS", "HRV", "HR", "HR", "HR", "XR", "XR",
            "SLO", "SI", "BIH", "BA", "MKD", "MK", "KOS", "XK", "ALB", "AL");

    public byte[] convertDrivers(MultipartFile file) {
        List<String> csvRows = new ArrayList<>();
        csvRows.add(DRIVER_HEADER);
        boolean inRegistrationTable = false;
        for (String line : pdfLines(file)) {
            if (line.contains("Nac") && line.contains("Ime") && line.contains("Prezime")) {
                inRegistrationTable = true;
                continue;
            }
            if (line.startsWith("Lista prijava je")) break;
            if (!inRegistrationTable) continue;
            String[] values = line.split("\\s+");
            if (values.length < 5 || !values[0].matches("\\d+")) continue;
            int nationalityIndex = nationalityIndex(values);
            if (nationalityIndex < 0) continue;
            int nameIndex = nationalityIndex + 1;
            if (nameIndex < values.length && values[nameIndex].matches("\\d+")) nameIndex++;
            if (nameIndex + 1 >= values.length) continue;
            csvRows.add(csv(values[nameIndex], values[nameIndex + 1], NATIONALITY_CODES.get(values[nationalityIndex])));
        }
        if (csvRows.size() == 1) throw badRequest("No driver rows were found in this PDF");
        return String.join("\n", csvRows).concat("\n").getBytes(java.nio.charset.StandardCharsets.UTF_8);
    }

    public byte[] convertSessionResults(MultipartFile file) {
        List<String> lines = pdfLines(file);
        List<String> csvRows = new ArrayList<>();
        csvRows.add(RESULT_HEADER);
        boolean inOverallClassification = false;
        for (int index = 0; index < lines.size(); index++) {
            String line = lines.get(index);
            if (line.equals("Time GENERALKA KLASA")) {
                inOverallClassification = true;
                continue;
            }
            if (!inOverallClassification) continue;
            if (line.contains("KATEGORIJA 2") || line.contains("KLASA H 1000")) break;

            if (FINISHED_ENTRY.matcher(line).matches() && index + 10 < lines.size()) {
                String[] start = line.split("\\s+");
                ResultRow row = resultRow(start[0], start[1], lines.get(index + 1), lines.get(index + 5), lines.get(index + 6), lines.get(index + 8), lines.get(index + 8), lines.get(index + 9), lines.get(index + 10));
                if (row != null) csvRows.add(row.csv());
            } else if (NON_FINISHER_ENTRY.matcher(line).matches() && index + 5 < lines.size()) {
                String[] start = line.split("\\s+");
                ResultRow row = resultRow("", start[0], lines.get(index + 1), nameAfterClass(lines.get(index + 1)), lines.get(index + 2), lines.get(index + 4), lines.get(index + 4), lines.get(index + 5), lines.get(index + 6));
                if (row != null) csvRows.add(row.csv());
            }
        }
        if (csvRows.size() == 1) throw badRequest("No overall result rows were found in this PDF");
        return String.join("\n", csvRows).concat("\n").getBytes(java.nio.charset.StandardCharsets.UTF_8);
    }

    private ResultRow resultRow(String position, String raceNumber, String classLine, String driverLine, String vehicleLine, String clubLine, String firstRunLine, String secondRunLine, String finalLine) {
        String[] driver = driverLine.trim().split("\\s+", 2);
        if (driver.length != 2) return null;
        String vehicle = vehicleLine.trim().replaceAll("\\s+", " ");
        String[] vehicleParts = vehicle.split("\\s+", 2);
        String status = status(finalLine);
        String club = clubName(clubLine);
        String firstRunStatus = status(firstRunLine);
        String secondRunStatus = status(secondRunLine);
        return new ResultRow(driver[0], driver[1], club, vehicleParts[0], vehicleParts.length == 2 ? vehicleParts[1] : "", vehicle,
                normalizeClass(classLine), raceNumber, timeMillis(firstRunLine), firstRunStatus, timeMillis(secondRunLine), secondRunStatus,
                status.equals("FINISHED") ? timeMillis(finalLine) : "", position, status);
    }

    private String nameAfterClass(String line) {
        String value = line.replaceFirst("^(?:D\\s*/\\s*E2\\s*/\\s*CN|E2SH|E1\\s+1200|[HNA]\\s+(?:1000|1150|1400|1600|2000\\s*\\+?))\\s+(?:E1-moto|[HNA])\\s+", "");
        return value;
    }

    private String normalizeClass(String value) {
        Matcher matcher = Pattern.compile("^(D\\s*/\\s*E2\\s*/\\s*CN|E2SH|E1\\s+1200|[HNA]\\s+(?:1000|1150|1400|1600|2000\\s*\\+?))").matcher(value.trim());
        String normalized = (matcher.find() ? matcher.group(1) : value).replaceAll("\\(.*", "").trim().replaceAll("\\s+", " ");
        if (normalized.startsWith("D / E2 / CN")) return "D/E2/CN";
        return normalized.replace("2000 +", "2000+");
    }

    private String clubName(String value) {
        Matcher matcher = CLUB_AND_RESULT.matcher(value.trim());
        return matcher.matches() ? matcher.group(1).trim() : value.trim();
    }

    private String status(String value) {
        String upper = value.toUpperCase(Locale.ROOT);
        if (upper.contains("DISQ")) return "DSQ";
        if (upper.contains("DNF")) return "DNF";
        if (upper.contains("DNS")) return "DNS";
        return "FINISHED";
    }

    private int nationalityIndex(String[] values) {
        for (int index = 1; index < values.length; index++) {
            if (NATIONALITY_CODES.containsKey(values[index])) return index;
        }
        return -1;
    }

    private String timeMillis(String value) {
        Matcher matcher = TIME.matcher(value);
        if (!matcher.find()) return "";
        long minutes = Long.parseLong(matcher.group(1));
        long seconds = Long.parseLong(matcher.group(2));
        long milliseconds = Long.parseLong(matcher.group(3));
        return Long.toString((minutes * 60 + seconds) * 1000 + milliseconds);
    }

    private List<String> pdfLines(MultipartFile file) {
        if (file.isEmpty()) throw badRequest("The PDF file is empty");
        try (PDDocument document = Loader.loadPDF(file.getBytes())) {
            String text = new PDFTextStripper().getText(document);
            return text.lines().map(String::trim).filter(line -> !line.isBlank()).toList();
        } catch (IOException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to read the PDF file", exception);
        }
    }

    private static String csv(String... values) {
        return String.join(",", java.util.Arrays.stream(values).map(value -> "\"" + value.replace("\"", "\"\"") + "\"").toList());
    }

    private ResponseStatusException badRequest(String message) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
    }

    private record ResultRow(String firstName, String lastName, String clubName, String manufacturer, String model, String displayName,
                             String classCode, String raceNumber, String firstRunTime, String firstRunStatus, String secondRunTime, String secondRunStatus,
                             String elapsedTimeMillis, String position, String status) {
        String csv() { return PdfConversionService.csv(firstName, lastName, clubName, manufacturer, model, displayName, classCode, raceNumber, firstRunTime, firstRunStatus, secondRunTime, secondRunStatus, elapsedTimeMillis, position, status); }
    }
}
