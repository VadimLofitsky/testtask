package testtask.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testtask.model.Language;
import testtask.service.response.ResponseState;
import testtask.service.response.ServiceResponse;

import java.util.List;

@Service
public class LanguageService {
    private DBService dbService;

    @Autowired
    public LanguageService(DBService dbService) {
        this.dbService = dbService;
    }

    public JsonNode getAll() {
        List<Language> all = dbService.getAll();
        return ServiceResponse.get(all);
    }

    public JsonNode getByName(String name) {
        Language language = dbService.getByName(name);
        return ServiceResponse.get(language);
    }

    public JsonNode save(Language language) {
        Language saved = dbService.save(language);
        return ServiceResponse.get(ResponseState.OK, saved);
    }

    public JsonNode edit(String name, Language editLang) {
        Language language = dbService.getByName(name);
        language.setDescription(editLang.getDescription());
        language.setRating(editLang.getRating());

        Language saved = dbService.save(language);
        return ServiceResponse.get(ResponseState.OK, saved);
    }

    public JsonNode delete(String name) {
        dbService.delete(name);

        return ServiceResponse.get(ResponseState.OK);
    }
}