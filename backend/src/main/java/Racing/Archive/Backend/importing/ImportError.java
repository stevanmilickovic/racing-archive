package Racing.Archive.Backend.importing;

public record ImportError(int rowNumber, String subject, String message) {
}
