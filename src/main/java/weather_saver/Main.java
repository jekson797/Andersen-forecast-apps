package weather_saver;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import forecast.Forecast;
import forecast.ForecastManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.Time;
import java.util.concurrent.TimeoutException;

public class Main {

    private static final String QUEUE_NAME = "Weather";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setExceptionHandler(new BasicExceptionHandler());

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            String[] weatherInfo = message.split(";");

            Forecast forecast = new Forecast();
            forecast.setDate(Date.valueOf(weatherInfo[0]));
            forecast.setTime(Time.valueOf(weatherInfo[1]));
            forecast.setLocation(weatherInfo[2]);
            forecast.setTemperature(Integer.parseInt(weatherInfo[3]));
            forecast.setStatus(weatherInfo[4]);

            ForecastManager manager = new ForecastManager();
            manager.saveForecast(forecast);
            System.out.println("[x] Received = " + message);
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
