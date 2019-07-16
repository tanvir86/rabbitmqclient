package com.practice.rabbitmq.practice;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;


import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Practice {

    public static void main(String[] argv) throws Exception {
        try {

            Scanner scanner = new Scanner(System.in);
            String message;
            int choice = 0,turn = 0;
            PublisherChannel queueLogPublisherChannel = new PublisherChannel("queue_log");
            while (!(message = scanner.next()).equalsIgnoreCase("X")){
                if(turn++%2 == 0){
                    choice = Integer.parseInt(message);
                }else{
                    switch(choice){
                        case 0 :
                            queueLogPublisherChannel.sendMessage(message);
                            break;
                        case 1 :
                            pub(message);
                            break;
                        default:
                            newConnectionMessage(message);

                    }
                }

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void pub(String message) throws Exception{
        Publisher QueueLogPublisher = new Publisher("QueueJoin");
        QueueLogPublisher.publish(message);
    }
    public static void newConnectionMessage(String message) throws Exception{
        System.out.println("New Connection Message");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.26.147");
        factory.setUsername("telenorTest");
        factory.setPassword("123321");
        factory.setVirtualHost("telenorTest");
        factory.setPort(5672);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("queue_log", true, false, false, null);

            channel.basicPublish("", "queue_log",
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
