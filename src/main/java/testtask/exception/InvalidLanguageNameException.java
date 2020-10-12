package testtask.exception;

public class InvalidLanguageNameException extends LanguageNameException {
    private String[] validNames;

    public InvalidLanguageNameException(String langName, String[] validNames) {
        super(langName);
        this.validNames = validNames;
    }

    public String[] getValidNames() {
        return validNames;
    }
}