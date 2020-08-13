package forecast;

public class Main {

    public static void main(String[] args) {
        ForecastManager forecastManager = new ForecastManager();
        Forecast forecast = forecastManager.selectLastForecastByLocation("Vitebsk");
        System.out.println(forecast);
    }
}
