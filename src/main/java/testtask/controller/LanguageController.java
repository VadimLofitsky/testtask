package testtask.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import testtask.model.Language;
import testtask.service.LanguageService;
import testtask.service.response.ServiceResponse;

import java.util.List;

@RestController
@RequestMapping("language")
public class LanguageController {
    private LanguageService service;

    @Autowired
    public LanguageController(LanguageService service) {
        this.service = service;
    }

    // Get all languages
    @GetMapping("")
    public JsonNode getAll() {
        List<Language> langs = service.getAll();

        return ServiceResponse.get(langs);
    }

    // Get specified language
    @GetMapping("{name}")
    public JsonNode get(@PathVariable String name) {
        Language lang = service.getByName(name);

        return ServiceResponse.get(lang);
    }

    // Add new language described by request body json
    @PostMapping("")
    public JsonNode add(@RequestBody Language newLang) {
        return service.save(newLang);
    }

    // Edit language
    @PutMapping("{name}")
    public JsonNode edit(@PathVariable String name, @RequestBody Language editLang) {
        Language language = service.getByName(name);
        language.setDescription(editLang.getDescription());
        language.setRating(editLang.getRating());

        return service.save(language);
    }

    // Delete specified language
    @DeleteMapping("{name}")
    public JsonNode delete(@PathVariable String name) {
        return service.delete(name);
    }
}