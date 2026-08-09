package Racing.Archive.Backend.importing;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ImportExceptionHandler {

    @ExceptionHandler(ImportValidationException.class)
    ResponseEntity<ImportErrorsResponse> handleValidation(ImportValidationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ImportErrorsResponse(exception.getErrors()));
    }

    record ImportErrorsResponse(List<ImportError> errors) {
    }
}
