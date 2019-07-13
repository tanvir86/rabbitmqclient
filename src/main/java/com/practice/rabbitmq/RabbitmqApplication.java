package com.practice.rabbitmq;
import com.practice.rabbitmq.producer.MessageProducer;

import java.util.concurrent.TimeUnit;

public class RabbitmqApplication {
    public static void main(String[] args) {
        System.out.println("Test RUN started");

        connectionTest();
    }

    public static void connectionTest(){
        MessageProducer messageProducer = new MessageProducer("admin","rabidmq@16263","/","172.16.26.147",5672);

        if(messageProducer.createConnection()){
            System.out.println("Connection Created");

            try{
                TimeUnit.MINUTES.sleep(3);
                messageProducer.closeConnection();
                System.out.println("Connection Closed");
            }catch(Exception ex){ex.printStackTrace();}

        }
    }
}
