package com.practice.rabbitmq.practice;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionProvider {
	private static  ConnectionFactory factory = null;
	private static Connection connection = null;

	private ConnectionProvider(){}

	public static Connection getConnection() throws TimeoutException , IOException {
		if(factory == null || connection == null){
			synchronized (ConnectionProvider.class){
				if(factory == null)
					factory = new ConnectionFactory();

				setHostConnectionCredential();

				connection = factory.newConnection();
			}
		}
		return connection;
	}

	private static void setHostConnectionCredential(){
		factory.setHost("172.16.26.147");
		factory.setUsername("telenorTest");
		factory.setPassword("123321");
		factory.setVirtualHost("telenorTest");
		factory.setPort(5672);
	}
}
