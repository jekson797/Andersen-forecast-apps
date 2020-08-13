package weather_producer.weather_getter;

import weather_producer.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class OpenWeatherMapGetter extends WeatherGetter {

    private static final String API_KEY = "28effdba04f7e72450917e329498a39e";
    private String location;

    public OpenWeatherMapGetter(Parser parser, String location) {
        super(parser);
        this.location = location;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, String> getWeather() {
        Map<String, String> result = null;
        try {
            URLConnection connection = createURL().openConnection();
            Map<String, Object> generalWeather = getParser().parse(getWeatherString(connection));
            Map<String, Object> weatherDetails = getWeatherDetails(generalWeather);
            result = (HashMap<String, String>) generalWeather.get("main");
            result.put("main", (String) weatherDetails.get("main"));
            result.put("location", (String) generalWeather.get("name"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private URL createURL() throws MalformedURLException {
        return new URL("https://api.openweathermap.org/data/2.5/weather?q=" + location
                + "&appid=" + API_KEY + "&units=metric");
    }

    private String getWeatherString(URLConnection connection) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getWeatherDetails(Map<String, Object> generalWeather) {
        List<Object> detailsList = (List<Object>) generalWeather.get("weather");
        Map<String, Object> details = null;
        if (detailsList.size() > 0) {
            details = (HashMap<String, Object>) detailsList.get(0);
        } else {
            System.err.println("Details not found");
        }
        return details;
    }
}
