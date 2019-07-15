package com.practice.rabbitmq.topics;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ReceiveLogsTopic {
    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.26.147");
        factory.setUsername("telenorTest");
        factory.setPassword("123321");
        factory.setVirtualHost("telenorTest");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();

        Scanner scanner = new Scanner(System.in);
        String bindingKey ;int i = 0;
        while(true){
            System.out.println("Usage: ReceiveLogsTopic [binding_key]...(X for close)");
            bindingKey = scanner.next();
            if(bindingKey.equalsIgnoreCase("X"))break;
            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
            i++;
        }
        if(i==0)channel.queueBind(queueName, EXCHANGE_NAME, "info");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");

        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }

}
