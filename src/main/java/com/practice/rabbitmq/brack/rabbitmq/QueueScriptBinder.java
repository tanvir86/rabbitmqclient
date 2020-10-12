package com.practice.rabbitmq.brack.rabbitmq;

public class QueueScriptBinder {
    private QueueScriptBinder(){
     }

     public static String getQueueName(String purpose){
        return purpose ;
     }

}
