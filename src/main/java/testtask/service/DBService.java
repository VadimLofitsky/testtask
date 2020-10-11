package testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testtask.model.Language;
import testtask.repository.LanguageRepository;

import java.util.List;

@Service
public class DBService {
    private LanguageRepository repository;

    @Autowired
    public DBService(LanguageRepository repository) {
        this.repository = repository;
    }

    public List<Language> getAll() {
        return repository.findAll();
    }

    public Language getByName(String name) {
        return repository.findByName(name);
    }

    public Language getByRating(int rating) {
        return repository.findByRating(rating);
    }

    public Language save(Language language) {
        return repository.save(language);
    }

    public void delete(String name) {
        repository.deleteByName(name);
    }
}