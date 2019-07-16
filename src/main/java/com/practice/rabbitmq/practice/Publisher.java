package com.practice.rabbitmq.practice;

import com.rabbitmq.client.AlreadyClosedException;

public class Publisher {
    //private PublisherChannel publisherChannel;
    private String purpose;
    Publisher(String purpose){
       this.purpose = purpose;
    }

    public void publish(String message){
        try{
            PublisherChannel publisherChannel = new PublisherChannel(QueueScriptBinder.getQueueName(this.purpose));
            publisherChannel.sendMessage(message);
            publisherChannel.close();
        }catch (Exception ex){
            if(ex instanceof AlreadyClosedException){
                System.out.println("Already Closed exception");
//                TODO
//                    Add failed message queue
            }
            ex.printStackTrace();
        }

    }
}
