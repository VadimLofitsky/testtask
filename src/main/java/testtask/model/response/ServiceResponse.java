package testtask.model.response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import testtask.model.Language;

import java.util.List;


// class for generating response messages (json)

public class ServiceResponse {
    private static ObjectMapper mapper = new ObjectMapper();

    // returns json containing list of persisted languages
    public static JsonNode get(List<Language> list) {
        return mapper.valueToTree(list);
    }

    // returns json containing the language only
    public static JsonNode get(Language language) {
        return mapper.valueToTree(language);
    }

    // returns json containing both state and language in 'body' property
    public static JsonNode get(ResponseState state, Language body) {
        ObjectNode node = mapper.createObjectNode();
        JsonNode bodyNode = mapper.valueToTree(body);

        node.put("state", state.toString());
        node.set("body", bodyNode);

        return node;
    }

    // returns json containing the state only
    public static JsonNode get(ResponseState state) {
        ObjectNode node = mapper.createObjectNode();
        node.put("state", state.toString());

        return node;
    }
}