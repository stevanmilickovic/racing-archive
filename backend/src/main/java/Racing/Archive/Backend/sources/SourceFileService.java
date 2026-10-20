package Racing.Archive.Backend.sources;

import Racing.Archive.Backend.domain.SourceDocument;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SourceFileService {
    private final Path directory;

    public SourceFileService(@Value("${racing-archive.sources-directory}") String directory) {
        this.directory = Path.of(directory).toAbsolutePath().normalize();
    }

    public Resource file(SourceDocument document) {
        try {
            Path relative = Path.of(document.getFilePath());
            if (relative.isAbsolute()) throw unavailable();
            Path candidate = directory.resolve(relative).normalize();
            if (!candidate.startsWith(directory)) throw unavailable();
            Path realFile = candidate.toRealPath();
            if (!realFile.startsWith(directory.toRealPath()) || !Files.isRegularFile(realFile)) throw unavailable();
            if (!checksum(realFile).equalsIgnoreCase(document.getSha256())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Source document differs from the registered original");
            }
            return new FileSystemResource(realFile);
        } catch (IOException | InvalidPathException exception) {
            throw unavailable();
        }
    }

    private String checksum(Path path) throws IOException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            try (InputStream stream = new DigestInputStream(Files.newInputStream(path), digest)) {
                stream.transferTo(java.io.OutputStream.nullOutputStream());
            }
            return HexFormat.of().formatHex(digest.digest());
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException(exception);
        }
    }

    private ResponseStatusException unavailable() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Source document is unavailable");
    }
}
