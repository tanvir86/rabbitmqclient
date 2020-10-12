package com.practice.rabbitmq.brack.rabbitmq;

import java.io.IOException;

public interface IPublisherChannel {

	void sendMessage(String message) throws IOException;
	void close() throws Exception;
}
