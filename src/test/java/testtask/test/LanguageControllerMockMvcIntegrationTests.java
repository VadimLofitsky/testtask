package testtask.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import testtask.exception.LanguageAlreadyExistsException;
import testtask.exception.NoSuchLanguageException;
import testtask.exception.RatingAlreadyExistsException;
import testtask.model.Language;
import testtask.model.response.ResponseState;
import testtask.repository.LanguageRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LanguageControllerMockMvcIntegrationTests {

    private final String urlPrefix = "http://localhost:8081/language/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LanguageRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void givenLanguage_whenAddNewValid_thenStatus200AndLanguageReturned() throws Exception {
        String name = "Java";
        int rating = 1;
        String description = "Java is so Java";

        Language language = new Language(name, description, rating);
        Mockito.when(repository.save(Mockito.any())).thenReturn(language);

        mockMvc.perform(post(urlPrefix)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.valueToTree(language).toString()))

                .andExpect(status().isOk())
//                .andExpect(contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("body.name").value(name))
                .andExpect(jsonPath("body.rating").value(rating))
                .andExpect(jsonPath("body.description").value(description));
    }

    @Test
    public void givenLanguage_whenAddExistingName_thenStatus400AndExceptionThrown() throws Exception {
        String name = "Java";
        int rating = 1;
        String description = "Java is so Java";

        Language language = new Language(name, description, rating);
        Mockito.when(repository.findByName(Mockito.any())).thenReturn(language);

        mockMvc.perform(post(urlPrefix)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.valueToTree(language).toString()))

                .andExpect(status().isBadRequest())
//                .andExpect(contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("state").value(ResponseState.ERROR.toString()))
                .andExpect(jsonPath("reason").value("Language already exists - " + name))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof LanguageAlreadyExistsException));
    }

    @Test
    public void givenName_whenAddExistingRating_thenStatus400AndExceptionThrown() throws Exception {
        String name = "Java";
        int rating = 1;
        String description = "Java is so Java";

        Language language = new Language(name, description, rating);
        Mockito.when(repository.findByRating(Mockito.anyInt())).thenReturn(language);

        mockMvc.perform(post(urlPrefix)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.valueToTree(language).toString()))

                .andExpect(status().isBadRequest())
//                .andExpect(contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("state").value(ResponseState.ERROR.toString()))
                .andExpect(jsonPath("reason").value("Rating already exists - " + rating))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RatingAlreadyExistsException));
    }

    @Test
    public void givenName_whenGet_thenStatus200AndLanguageReturned() throws Exception {
        String name = "Java";
        int rating = 1;
        String description = "Java is so Java";

        Language language = new Language(name, description, rating);
        Mockito.when(repository.findByName(Mockito.any())).thenReturn(language);

        mockMvc.perform(get(urlPrefix + "name")
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.valueToTree(language).toString()))

                .andExpect(status().isOk())
//                .andExpect(contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name").value(name))
                .andExpect(jsonPath("rating").value(rating))
                .andExpect(jsonPath("description").value(description));
    }

    @Test
    public void givenInvalidName_whenGet_thenStatus404AndExceptionThrown() throws Exception {
        String name = "Java";

        Mockito.when(repository.findByName(Mockito.any())).thenReturn(null);

        mockMvc.perform(get(urlPrefix + name)
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isNotFound())
//                .andExpect(contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("state").value(ResponseState.ERROR.toString()))
                .andExpect(jsonPath("reason").value("No such language - " + name))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoSuchLanguageException));
    }
}