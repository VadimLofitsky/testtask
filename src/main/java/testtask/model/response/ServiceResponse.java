package testtask.model.response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import testtask.model.Language;

import java.util.List;


public class ServiceResponse {
    private static ObjectMapper mapper = new ObjectMapper();

    public static JsonNode get(List<Language> list) {
        return mapper.valueToTree(list);
    }

    public static JsonNode get(Language language) {
        return mapper.valueToTree(language);
    }

    public static JsonNode get(ResponseState state, Language body) {
        ObjectNode node = mapper.createObjectNode();
        JsonNode bodyNode = mapper.valueToTree(body);

        node.put("state", state.toString());
        node.set("body", bodyNode);

        return node;
    }

    public static JsonNode get(ResponseState state) {
        ObjectNode node = mapper.createObjectNode();
        node.put("state", state.toString());

        return node;
    }
}