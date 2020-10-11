package testtask.exception;

public class NoSuchLanguageException extends RuntimeException {
    private String langName;

    public NoSuchLanguageException(String langName) {
        this.langName = langName;
    }

    public String getLangName() {
        return langName;
    }
}