package weather_producer;

import weather_producer.parser.JsonParser;
import weather_producer.weather_getter.OpenWeatherMapGetter;
import weather_producer.weather_getter.WeatherGetter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Map;

public class Main {

    private static final String QUEUE_NAME = "Weather";

    public static void main(String[] args) {
        WeatherGetter weatherGetter = new OpenWeatherMapGetter(new JsonParser(), "Vitebsk");
        Map<String, String> weather = weatherGetter.getWeather();
        if (weather != null) {
            MessageSender sender = new MessageSender(QUEUE_NAME);
            sender.send(generateWeatherString(weather));
        } else {
            System.err.println("Getting Weather ERROR!");
        }
    }

    private static String generateWeatherString(Map<String, String> weather) {
        return LocalDate.now(ZoneId.systemDefault()) + ";"
                + LocalTime.now(ZoneId.systemDefault()).getHour() + ":00:00" + ";"
                + weather.get("location") + ";"
                + String.valueOf(weather.get("temp")) + ";"
                + weather.get("main");
    }
}
