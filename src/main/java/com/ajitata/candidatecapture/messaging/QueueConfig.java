package com.ajitata.candidatecapture.messaging;

public class QueueConfig {
    public static final String ROUTING_KEY = "ssce_int";

    public static final String QUEUE_EXCHANGE = "x.ssce-int-direct-exchange";

    public static final String AMQP_URL = "amqp://guest:guest@localhost:5672/";
}
