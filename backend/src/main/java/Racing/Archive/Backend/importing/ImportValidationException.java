package Racing.Archive.Backend.importing;

import java.util.List;

public class ImportValidationException extends RuntimeException {

    private final List<ImportError> errors;

    public ImportValidationException(List<ImportError> errors) {
        super("The import file contains validation errors");
        this.errors = List.copyOf(errors);
    }

    public List<ImportError> getErrors() {
        return errors;
    }
}
