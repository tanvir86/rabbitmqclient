package com.practice.rabbitmq.hello;

public class senderMain {

    public static void main(String[] args) {
        System.out.println("Test RUN started");

        Send send = new Send();

        send.helloMessageSend();
    }
}
