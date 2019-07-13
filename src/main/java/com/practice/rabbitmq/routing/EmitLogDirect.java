package com.practice.rabbitmq.routing;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EmitLogDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.26.147");
        factory.setUsername("admin");
        factory.setPassword("rabidmq@16263");
        factory.setVirtualHost("/");
        factory.setPort(5672);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            Scanner scanner = new Scanner(System.in);
            String message,severity;
            while(true){
                System.out.println("Write new Message:(X for close)");
                message = scanner.next();

                if(message.equalsIgnoreCase("X"))break;

                severity = scanner.next();

                channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
            }
        }
    }
}
