package testtask.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import testtask.model.Language;
import testtask.repository.LanguageRepository;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class LanguageRepositoryIntegrationTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private LanguageRepository repository;

    @Test
    public void whenFindByName_thenReturnLanguage() {
        // given
        String name = "Java";
        String descr = "Java is so Java";
        int rating = 1;

        Language lang = new Language(name, descr, rating);
        entityManager.persist(lang);
        entityManager.flush();

        // when
        Language found = repository.findByName(name);

        // then
        assertEquals(name, found.getName());
    }
}