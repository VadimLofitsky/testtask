package testtask.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class LanguageExceptionHandler extends ResponseEntityExceptionHandler {

    @Data
    @AllArgsConstructor
    private static final class LanguageException {
        private String reason;
    }

    @ExceptionHandler(NoSuchLanguageException.class)
    protected ResponseEntity<LanguageException> handleNoSuchLanguageException(NoSuchLanguageException e) {
        LanguageException languageException = new LanguageException("No such language - " + e.getLangName());

        return new ResponseEntity<>(languageException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LanguageNameMissedException.class)
    protected ResponseEntity<LanguageException> handleLanguageNameMissedException(LanguageNameMissedException e) {
        LanguageException languageException = new LanguageException("Language name missed");

        return new ResponseEntity<>(languageException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LanguageAlreadyExistsException.class)
    protected ResponseEntity<LanguageException> handleLanguageAlreadyExistsException(LanguageAlreadyExistsException e) {
        LanguageException languageException = new LanguageException("Language already exists - " + e.getLangName());

        return new ResponseEntity<>(languageException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RatingAlreadyExistsException.class)
    protected ResponseEntity<LanguageException> handleRatingAlreadyExistsException(RatingAlreadyExistsException e) {
        LanguageException languageException = new LanguageException("Rating already exists - " + e.getRating());

        return new ResponseEntity<>(languageException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidLanguageNameException.class)
    protected ResponseEntity<LanguageException> handleInvalidLanguageNameException(InvalidLanguageNameException e) {
        String reason = new StringBuilder().append("Invalid language name - " + e.getLangName())
                .append(". Must be one of " + Arrays.toString(e.getValidNames()))
                .toString();

        LanguageException languageException = new LanguageException(reason);

        return new ResponseEntity<>(languageException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidLanguageRatingException.class)
    protected ResponseEntity<LanguageException> handleInvalidLanguageRatingException(InvalidLanguageRatingException e) {
        String reason = new StringBuilder().append("Invalid language rating - " + e.getRating())
                                           .append(". Must be between " + e.getMin())
                                           .append(" and  " + e.getMax())
                                           .toString();

        LanguageException languageException = new LanguageException(reason);

        return new ResponseEntity<>(languageException, HttpStatus.BAD_REQUEST);
    }
}