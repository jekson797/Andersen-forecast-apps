package weather_producer.weather_getter;

import weather_producer.parser.Parser;

import java.util.Map;

public abstract class WeatherGetter {

    Parser parser;

    public WeatherGetter(Parser parser) {
        this.parser = parser;
    }

    public abstract Map<String, String> getWeather();

    protected Parser getParser() {
        return parser;
    }
}
