package testtask.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


// parent class for exceptions those need to point invalid rating

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LanguageRatingException extends RuntimeException {
    private int rating;
}