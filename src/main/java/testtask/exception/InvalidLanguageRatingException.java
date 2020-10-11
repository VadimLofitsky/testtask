package testtask.exception;

public class InvalidLanguageRatingException extends RuntimeException {
    private int rating, min, max;

    public InvalidLanguageRatingException(int rating, int min, int max) {
        this.rating = rating;
        this.min = min;
        this.max = max;
    }

    public int getRating() {
        return rating;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}