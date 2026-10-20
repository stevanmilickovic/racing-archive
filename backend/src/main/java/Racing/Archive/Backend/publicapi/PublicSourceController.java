package Racing.Archive.Backend.publicapi;

import Racing.Archive.Backend.domain.SourceDocument;
import Racing.Archive.Backend.repository.SourceDocumentRepository;
import Racing.Archive.Backend.sources.SourceFileService;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PublicSourceController {
    private final SourceDocumentRepository documents;
    private final SourceFileService files;

    @GetMapping("/rounds/{roundId}/sources")
    List<PublicSourceResponse> sources(@PathVariable Long roundId) {
        return documents.findByRoundIdOrderByIdAsc(roundId).stream().map(this::response).toList();
    }

    @GetMapping("/sources/{id}/download")
    ResponseEntity<Resource> download(@PathVariable Long id) {
        SourceDocument document = documents.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Resource file = files.file(document);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getMediaType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                        .filename(file.getFilename(), StandardCharsets.UTF_8).build().toString())
                .header("X-Content-Type-Options", "nosniff")
                .eTag(document.getSha256())
                .body(file);
    }

    private PublicSourceResponse response(SourceDocument document) {
        return new PublicSourceResponse(document.getId(), document.getTitle(), document.getMediaType(),
                document.getSha256(), document.getOriginalUrl(), document.getNotes(),
                "/public/sources/" + document.getId() + "/download");
    }
}

record PublicSourceResponse(Long id, String title, String mediaType, String sha256,
                           String originalUrl, String notes, String downloadUrl) {}
