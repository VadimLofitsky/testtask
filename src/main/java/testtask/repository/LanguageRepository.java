package testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testtask.model.Language;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    List<Language> findAll();
    Language findByName(String name);

    @Transactional
    void deleteByName(String name);
}