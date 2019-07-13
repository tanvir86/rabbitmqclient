package com.practice.rabbitmq.hello;

public class ReceiverMain {

    public static void main(String[] args) {
        System.out.println("Test RUN started");

        Receive receive = new Receive();

        receive.recieveFromQueue();
    }
}
