package com.practice.rabbitmq.practice;

import java.util.HashMap;

public class QueueScriptBinder {
    private static HashMap<String,String> QueueMap ;
    static{
        QueueMap = new HashMap<>();
        QueueMap.put("QueueJoin","queue_log");
    }
    private QueueScriptBinder(){
     }

     public static String getQueueName(String purpose){
        return QueueMap.get(purpose) == null ? "default_queue" : QueueMap.get(purpose) ;
     }

}
