package testtask.exception;

public class RatingAlreadyExistsException extends RuntimeException {
    private int rating;

    public RatingAlreadyExistsException(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }
}