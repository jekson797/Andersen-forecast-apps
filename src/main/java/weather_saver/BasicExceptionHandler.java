package weather_saver;

import com.rabbitmq.client.*;

public class BasicExceptionHandler implements ExceptionHandler {
    @Override
    public void handleUnexpectedConnectionDriverException(Connection connection, Throwable throwable) {

    }

    @Override
    public void handleReturnListenerException(Channel channel, Throwable throwable) {

    }

    @Override
    public void handleConfirmListenerException(Channel channel, Throwable throwable) {

    }

    @Override
    public void handleBlockedListenerException(Connection connection, Throwable throwable) {

    }

    @Override
    public void handleConsumerException(Channel channel, Throwable throwable, Consumer consumer, String s, String s1) {
        System.out.println(" - error raised by: " + channel.getChannelNumber());
    }

    @Override
    public void handleConnectionRecoveryException(Connection connection, Throwable throwable) {

    }

    @Override
    public void handleChannelRecoveryException(Channel channel, Throwable throwable) {

    }

    @Override
    public void handleTopologyRecoveryException(Connection connection, Channel channel, TopologyRecoveryException e) {

    }
}
