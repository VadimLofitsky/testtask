package testtask.exception;

public class InvalidLanguageRatingException extends LanguageRatingException {
    private int min, max;

    public InvalidLanguageRatingException(int rating, int min, int max) {
        super(rating);
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}