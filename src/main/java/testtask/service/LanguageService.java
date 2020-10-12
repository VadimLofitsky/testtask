package testtask.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testtask.exception.LanguageAlreadyExistsException;
import testtask.exception.LanguageNameMissedException;
import testtask.exception.NoSuchLanguageException;
import testtask.exception.RatingAlreadyExistsException;
import testtask.model.Language;
import testtask.model.response.ResponseState;
import testtask.model.response.ServiceResponse;
import testtask.validation.LanguageValidator;

import java.util.List;

@Service
public class LanguageService {
    private DBService dbService;
    private LanguageValidator validator;

    @Autowired
    public LanguageService(DBService dbService, LanguageValidator validator) {
        this.dbService = dbService;
        this.validator = validator;
    }

    public JsonNode getAll() {
        List<Language> all = dbService.getAll();
        return ServiceResponse.get(all);
    }

    public JsonNode getByName(String name) {
        Language language = dbService.getByName(name);

        if (language == null) {
            throw new NoSuchLanguageException(name);
        }

        return ServiceResponse.get(language);
    }

    public JsonNode save(Language language) {
        Language saved = dbService.save(language);

        return ServiceResponse.get(ResponseState.OK, saved);
    }

    public JsonNode add(Language newLang) {
        String name = newLang.getName();
        int rating = newLang.getRating();

        Language byName = dbService.getByName(name);
        Language byRating = dbService.getByRating(rating);

        if (name == null) {
            throw new LanguageNameMissedException();
        }

        if (byName != null) {
            throw new LanguageAlreadyExistsException(name);
        }

        if (byRating != null) {
            throw new RatingAlreadyExistsException(rating);
        }

        validator.validateName(name);
        validator.validateRating(rating);

        String description = validator.validateDescription(newLang.getDescription());
        newLang.setDescription(description);

        return save(newLang);
    }

    public JsonNode edit(String name, Language editLang) {
        int newRating = editLang.getRating();
        validator.validateRating(newRating);

        Language language = dbService.getByName(name);
        if (language == null) {
            throw new NoSuchLanguageException(name);
        }

        Language byRating = dbService.getByRating(newRating);
        if ((newRating != language.getRating()) && (byRating != null)) {
            throw new RatingAlreadyExistsException(newRating);
        }

        String description = validator.validateDescription(editLang.getDescription());
        language.setDescription(description);

        language.setRating(newRating);

        return save(language);
    }

    public JsonNode delete(String name) {
        Language byName = dbService.getByName(name);
        if (byName == null) {
            throw new NoSuchLanguageException(name);
        }

        dbService.delete(name);

        return ServiceResponse.get(ResponseState.OK);
    }
}