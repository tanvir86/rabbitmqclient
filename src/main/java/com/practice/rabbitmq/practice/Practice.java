package com.practice.rabbitmq.practice;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;

public class Practice {

    public static void main(String[] argv) throws Exception {
        try {
            PublisherChannel queueLogPublisherChannel = new PublisherChannel("queue_log");
            Scanner scanner = new Scanner(System.in);
            String message;
            while (!(message = scanner.next()).equalsIgnoreCase("X"))
                queueLogPublisherChannel.sendMessage(message);
            queueLogPublisherChannel.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
