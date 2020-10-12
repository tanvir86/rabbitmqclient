package com.practice.rabbitmq.brack.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class BasicPublisherChannel implements IPublisherChannel {
	private String exchangeName;
	private Channel channel;
	private Connection connection;

	BasicPublisherChannel(String exchangeName) throws Exception{
		this.exchangeName = exchangeName;
		this.connection = ConnectionProvider.getConnection();
		this.channel = this.connection.createChannel();
		this.exchangeDeclare();
	}

	private void exchangeDeclare() throws IOException {
		this.channel.exchangeDeclare(exchangeName, "fanout",true);
	}

	public void sendMessage(String message) throws IOException {
		channel.basicPublish(exchangeName,"", null,message.getBytes(StandardCharsets.UTF_8));
	}

	public void close() throws Exception{
		channel.close();
	}
}
