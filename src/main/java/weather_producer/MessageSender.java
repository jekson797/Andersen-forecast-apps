package weather_producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class MessageSender {

    private final String QUEUE_NAME;

    public MessageSender(String QUEUE_NAME) {
        this.QUEUE_NAME = QUEUE_NAME;
    }

    public void send(String message) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("[x] Sent = " + message);
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
