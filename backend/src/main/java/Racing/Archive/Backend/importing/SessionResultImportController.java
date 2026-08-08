package Racing.Archive.Backend.importing;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class SessionResultImportController {

    private final SessionResultImportService sessionResultImportService;

    @GetMapping(value = "/import-templates/session-results", produces = "text/csv")
    ResponseEntity<byte[]> downloadTemplate() throws IOException {
        ClassPathResource template = new ClassPathResource("import-templates/session-results.csv");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename("session-results.csv").build().toString())
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(template.getInputStream().readAllBytes());
    }

    @PostMapping(value = "/sessions/{sessionId}/results/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    SessionResultImportSummary importResults(@PathVariable Long sessionId, @RequestParam("file") MultipartFile file) {
        return sessionResultImportService.importFile(sessionId, file);
    }
}
