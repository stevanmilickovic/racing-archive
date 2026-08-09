package Racing.Archive.Backend.importing;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/pdf-conversions")
@RequiredArgsConstructor
public class PdfConversionController {

    private final PdfConversionService pdfConversionService;

    @PostMapping(value = "/drivers", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "text/csv")
    ResponseEntity<byte[]> convertDrivers(@RequestParam("file") MultipartFile file) {
        return csv("drivers-converted.csv", pdfConversionService.convertDrivers(file));
    }

    @PostMapping(value = "/session-results", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "text/csv")
    ResponseEntity<byte[]> convertSessionResults(@RequestParam("file") MultipartFile file) {
        return csv("session-results-converted.csv", pdfConversionService.convertSessionResults(file));
    }

    private ResponseEntity<byte[]> csv(String filename, byte[] body) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename(filename).build().toString())
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(body);
    }
}
