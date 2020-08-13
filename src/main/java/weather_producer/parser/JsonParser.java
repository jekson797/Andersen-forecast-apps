package weather_producer.parser;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JsonParser extends Parser {

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> parse(String stringToParse) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> result = null;
        try {
            result = mapper.readValue(stringToParse, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
