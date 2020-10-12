package testtask.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import testtask.exception.InvalidLanguageNameException;
import testtask.exception.InvalidLanguageRatingException;
import testtask.service.DBService;


// class with validation methods for language name and rating

@Component
@PropertySource("classpath:langValidation.properties")
public class LanguageValidator {

    @Autowired
    DBService dbService;

    // minimal valid rating value
    @Value("${rating.min}")
    private int RATING_MIN;

    // maximal valid rating
    @Value("${rating.max}")
    private int RATING_MAX;

    // list of valid language names
    @Value("${validNames}")
    private String[] VALID_NAMES;

    // checks whether the specified name is in the list of valid names
    // otherwise throws an exception
    public void validateName(String name) {
        for (String langName : VALID_NAMES) {
            if (name.equals(langName)) {
                return;
            }
        }

        throw new InvalidLanguageNameException(name, VALID_NAMES);
    }

    // checks whether the specified rating is inside of valid range
    // otherwise throws an exception
    public void validateRating(int rating) {
        if (rating < RATING_MIN || rating > RATING_MAX) {
            throw new InvalidLanguageRatingException(rating, RATING_MIN, RATING_MAX);
        }
    }

    // replace absent description with an empty string
    public String validateDescription(String description) {
        return description == null ? "" : description;
    }
}