package com.practice.rabbitmq.hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
    private final static String QUEUE_NAME = "hello";

    public void helloMessageSend (){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.26.147");
        factory.setUsername("admin");
        factory.setPassword("rabidmq@16263");
        factory.setVirtualHost("/");
        factory.setPort(5672);
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
