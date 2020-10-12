package com.practice.rabbitmq.brack.rabbitmq;

import com.rabbitmq.client.AlreadyClosedException;
//import org.apache.log4j.Logger;

public class Publisher {
    //private PublisherChannel publisherChannel;
//	private final static Logger logger = Logger.getLogger(Publisher.class);
    private String purpose;
    public Publisher(String purpose){
       this.purpose = purpose;
    }

    public void publish(String message){
        try{
            IPublisherChannel publisherChannel = new PersistentPublisherChannel(QueueScriptBinder.getQueueName(this.purpose));
            publisherChannel.sendMessage(message);
            publisherChannel.close();
        }catch (Exception ex){
            if(ex instanceof AlreadyClosedException){
				System.out.println("[RabbitMQ]Already Closed exception :");
//                TODO
//                    Add failed message queue

            }
			System.out.println("[RabbitMQ]Message published failed to :"+this.purpose+"with message:"+message);
        }

    }

	public void basicPublish(String message){
		try{
			IPublisherChannel publisherChannel = new BasicPublisherChannel(QueueScriptBinder.getQueueName(this.purpose));
			publisherChannel.sendMessage(message);
			publisherChannel.close();
		}catch (Exception ex){
			if(ex instanceof AlreadyClosedException){
//				logger.error("[RabbitMQ]Already Closed exception :");
				System.out.println("[RabbitMQ]Already Closed exception :");
//                TODO
//                    Add failed message queue
			}
			System.out.println("[RabbitMQ]Message published failed to :"+this.purpose+"with message:"+message);
		}

	}
}
