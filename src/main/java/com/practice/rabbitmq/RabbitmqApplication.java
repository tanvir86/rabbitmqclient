package com.practice.rabbitmq;
import com.practice.rabbitmq.brack.rabbitmq.PublishMessage;
import com.practice.rabbitmq.producer.MessageProducer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class RabbitmqApplication {
    public static void main(String[] args) {
        System.out.println("Test RUN started");

        connectionTest();
//        readLogFile();
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

    public static void sendCdrToAsteriskApi(String message ){
//        String message = "{\"msisdn\":\" 8801775323381\",\"callUniqueId\":\"1601383000.44174\",\"callStartTime\":\"2020-09-29 18:36:40\",\"channelEndDateTime\":\"2020-09-29 18:36:54\",\"callerName\":null,\"callerId\":\"+8801775323381\",\"destinationId\":\"789999\",\"lastContextName\":\"hangup_handler_telenor_demo_context\",\"destinationChannelId\":\"789999\",\"callStatus\":\"ANSWERED\",\"answerTime\":\"2020-09-29 18:36:40\",\"orgCode\":\"\",\"membershipType\":\"\",\"campaignCode\":\"\"}";
        PublishMessage paPublishMessage = new PublishMessage();
        paPublishMessage.messagePubish(message,"custom_cdr_for_astrisk_api");
    }

    public static void readLogFile(){
        try {
            File myObj = new File("C:\\Users\\Tanvir Ahmed\\Desktop\\New folder\\missing.log");
            Scanner myReader = new Scanner(myObj);
            int i=0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
//                sendCdrToAsteriskApi(data);
                i++;
            }
            myReader.close();
            System.out.println(i);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
