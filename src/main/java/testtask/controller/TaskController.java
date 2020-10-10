package testtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import testtask.model.Language;
import testtask.service.LanguageService;

import java.util.List;

@RestController
@RequestMapping("language")
public class TaskController {
    private LanguageService service;

    @Autowired
    public TaskController(LanguageService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Language> getAll() {
        return service.getAll();
    }

    @GetMapping("{name}")
    public Language get(@PathVariable String name) {
        return service.getByName(name);
    }

    @PostMapping("")
    public Language add(@RequestBody Language newLang) {
        System.out.println("called POST with " + newLang);
        return service.save(newLang);
    }

    @PutMapping("{name}")
    public Language edit(@PathVariable String name, @RequestBody Language editLang) {
        System.out.println("called PUT with " + editLang);

        Language language = service.getByName(name);
        System.out.println("stored = " + language);
        language.setDescription(editLang.getDescription());
        language.setRating(editLang.getRating());

        return service.save(language);
    }

    @DeleteMapping("{name}")
    public void delete(@PathVariable String name) {
        service.delete(name);
    }
}