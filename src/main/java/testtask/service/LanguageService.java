package testtask.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testtask.model.Language;
import testtask.repository.LanguageRepository;
import testtask.service.response.ResponseState;
import testtask.service.response.ServiceResponse;

import java.util.List;

@Service
public class LanguageService {
    private LanguageRepository repository;

    @Autowired
    public LanguageService(LanguageRepository repository) {
        this.repository = repository;
    }

    public List<Language> getAll() {
        return repository.findAll();
    }

    public Language getByName(String name) {
        return repository.findByName(name);
    }

    public JsonNode save(Language language) {
        Language saved = repository.save(language);
        JsonNode response = ServiceResponse.get(ResponseState.OK, saved);

        return response;
    }

    public JsonNode delete(String name) {
        repository.deleteByName(name);

        return ServiceResponse.get(ResponseState.OK);
    }
}