package Racing.Archive.Backend.sources;

import Racing.Archive.Backend.domain.SourceDocument;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.HexFormat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import static org.assertj.core.api.Assertions.*;

class SourceFileServiceTest {
    @TempDir Path temporary;

    @Test
    void servesOnlyTheRegisteredOriginalAndRejectsChangedBytes() throws Exception {
        Path root = Files.createDirectory(temporary.resolve("events"));
        byte[] original = "Original race document".getBytes(java.nio.charset.StandardCharsets.UTF_8);
        Files.write(root.resolve("results.pdf"), original);
        SourceDocument document = document("results.pdf",
                HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(original)));
        SourceFileService service = new SourceFileService(root.toString());
        assertThat(service.file(document).getContentAsByteArray()).isEqualTo(original);
        Files.writeString(root.resolve("results.pdf"), "Different document");
        assertThatThrownBy(() -> service.file(document)).isInstanceOfSatisfying(
                ResponseStatusException.class, error -> assertThat(error.getStatusCode()).isEqualTo(HttpStatus.CONFLICT));
    }

    @Test
    void rejectsAbsolutePathsTraversalAndMissingFiles() throws Exception {
        Path root = Files.createDirectory(temporary.resolve("events"));
        Files.writeString(temporary.resolve("private.txt"), "Private review");
        SourceFileService service = new SourceFileService(root.toString());
        for (String path : new String[] { "../private.txt", temporary.resolve("private.txt").toString(), "missing.pdf" }) {
            assertThatThrownBy(() -> service.file(document(path, "0".repeat(64)))).isInstanceOfSatisfying(
                    ResponseStatusException.class, error -> assertThat(error.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND));
        }
    }

    private SourceDocument document(String path, String checksum) {
        SourceDocument document = new SourceDocument();
        document.setFilePath(path);
        document.setSha256(checksum);
        return document;
    }
}
