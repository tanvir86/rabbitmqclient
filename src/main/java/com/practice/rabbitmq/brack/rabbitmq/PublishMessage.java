package com.practice.rabbitmq.brack.rabbitmq;

public class PublishMessage {
    public void messagePubish(String full_message, String queue_name) {
//        queue_name = "custom_cdr_for_astrisk_api";
        Publisher publisher = new Publisher(queue_name);// seeQueueScriptBinder for classname and queuename binding
        System.out.println("Publish in ::" + queue_name + " Message ::" + full_message);
        publisher.publish(full_message);
    }

}
