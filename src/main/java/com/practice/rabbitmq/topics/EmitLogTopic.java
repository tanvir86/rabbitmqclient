package com.practice.rabbitmq.topics;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EmitLogTopic {
    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.26.147");
        factory.setUsername("telenorTest");
        factory.setPassword("123321");
        factory.setVirtualHost("telenorTest");
        factory.setPort(5672);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");


            Scanner scanner = new Scanner(System.in);
            String message,routingKey;
            while(true){
                System.out.println("Write new Message:(X for close)");
                message = scanner.next();
                if(message.equalsIgnoreCase("X"))break;

                System.out.println("Write Routing Key:");
                routingKey = scanner.next();

                channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
            }

        }
    }




}
