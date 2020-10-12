package testtask.exception;

public class RatingAlreadyExistsException extends LanguageRatingException {
    public RatingAlreadyExistsException(int rating) {
        super(rating);
    }
}