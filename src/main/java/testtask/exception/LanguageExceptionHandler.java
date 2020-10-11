package testtask.exception;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import testtask.service.response.ResponseState;
import testtask.service.response.ServiceResponse;

@ControllerAdvice
public class LanguageExceptionHandler extends ResponseEntityExceptionHandler {

    @Data
    @AllArgsConstructor
    private static final class LanguageException {
        private String reason;
    }

    @ExceptionHandler(NoSuchLanguageException.class)
    protected ResponseEntity<LanguageException> handleNoSuchLanguageException(NoSuchLanguageException e) {
        JsonNode response = ServiceResponse.get(ResponseState.ERROR);
        LanguageException languageException = new LanguageException("No such language - " + e.getLangName());

        return new ResponseEntity<>(languageException, HttpStatus.NOT_FOUND);
    }
}