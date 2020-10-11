package testtask.exception;

public class InvalidLanguageNameException extends RuntimeException {
    private String langName;
    private String[] validNames;

    public InvalidLanguageNameException(String langName, String[] validNames) {
        this.langName = langName;
        this.validNames = validNames;
    }

    public String getLangName() {
        return langName;
    }

    public String[] getValidNames() {
        return validNames;
    }
}