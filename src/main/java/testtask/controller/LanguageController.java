package testtask.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import testtask.model.Language;
import testtask.service.LanguageService;

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
        return service.getAll();
    }

    // Get specified language
    @GetMapping("{name}")
    public JsonNode get(@PathVariable String name) {
        return service.getByName(name);
    }

    // Add new language described by request body json
    @PostMapping("")
    public JsonNode add(@RequestBody Language newLang) {
        return service.save(newLang);
    }

    // Edit language
    @PutMapping("{name}")
    public JsonNode edit(@PathVariable String name, @RequestBody Language editLang) {
        return service.edit(name, editLang);
    }

    // Delete specified language
    @DeleteMapping("{name}")
    public JsonNode delete(@PathVariable String name) {
        return service.delete(name);
    }
}