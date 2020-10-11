package testtask.exception;

public class LanguageAlreadyExistsException extends RuntimeException {
    private String langName;

    public LanguageAlreadyExistsException(String langName) {
        this.langName = langName;
    }

    public String getLangName() {
        return langName;
    }
}