package com.practice.rabbitmq.brack.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public  class PersistentPublisherChannel implements IPublisherChannel{
    private String queueName;
    private Channel channel;
    private Connection connection;

	PersistentPublisherChannel(String queueName) throws Exception{
        this.queueName = queueName;
        this.connection = ConnectionProvider.getConnection();
        this.channel = this.connection.createChannel();
        this.queueDeclare();
    }


    private void queueDeclare() throws IOException {
        this.channel.queueDeclare(queueName,true,false,false,null);
    }

    public void sendMessage(String message) throws IOException{
        channel.basicPublish("",queueName, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes(StandardCharsets.UTF_8));
    }

	public void close() throws Exception{
		channel.close();
	}
}
