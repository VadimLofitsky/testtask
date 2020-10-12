package testtask.exception;

public class LanguageAlreadyExistsException extends LanguageNameException {
    public LanguageAlreadyExistsException(String langName) {
        super(langName);
    }
}