package weather_producer.parser;

import java.util.Map;

public abstract class Parser {

    public abstract Map<String, Object> parse(String stringToParse);

}
