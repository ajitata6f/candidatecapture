package com.ajitata.candidatecapture.messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class MessagePublisher {

    ConnectionFactory factory = new ConnectionFactory();

    public Connection getConnection () throws IOException, TimeoutException {

        return factory.newConnection(QueueConfig.AMQP_URL);
    }

    public Channel getChannel (Connection connection) throws IOException {
        return connection.createChannel();
    }

}
