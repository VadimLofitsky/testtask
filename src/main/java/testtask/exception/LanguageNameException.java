package testtask.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


// parent class for exceptions those need to point invalid name

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LanguageNameException extends RuntimeException {
    String langName;
}