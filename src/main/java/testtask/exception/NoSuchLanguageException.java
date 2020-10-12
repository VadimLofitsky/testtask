package testtask.exception;

public class NoSuchLanguageException extends LanguageNameException {
    public NoSuchLanguageException(String langName) {
        super(langName);
    }
}