package testtask.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import testtask.exception.InvalidLanguageNameException;
import testtask.exception.InvalidLanguageRatingException;
import testtask.service.DBService;

@Component
@PropertySource("classpath:langValidation.properties")
public class LanguageValidator {

    @Autowired
    DBService dbService;

    @Value("${rating.min}")
    private int RATING_MIN;

    @Value("${rating.max}")
    private int RATING_MAX;

    @Value("${validNames}")
    private String[] VALID_NAMES;


    public void validateName(String name) {
        for (String langName : VALID_NAMES) {
            if (name.equals(langName)) {
                return;
            }
        }

        throw new InvalidLanguageNameException(name, VALID_NAMES);
    }

    public void validateRating(int rating) {
        if (rating < RATING_MIN || rating > RATING_MAX) {
            throw new InvalidLanguageRatingException(rating, RATING_MIN, RATING_MAX);
        }
    }

    public String validateDescription(String description) {
        return description == null ? "" : description;
    }
}