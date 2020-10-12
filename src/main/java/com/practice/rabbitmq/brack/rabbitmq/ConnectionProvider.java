package com.practice.rabbitmq.brack.rabbitmq;

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
		factory.setHost("172.16.26.129");
		factory.setUsername("brack");
		factory.setPassword("brack@callcenter321");
		factory.setVirtualHost("brack");
		factory.setPort(5672);
	}
}
