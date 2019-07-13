package com.practice.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MessageProducer {

    private ConnectionFactory factory ;
    private Connection connection;
    private Channel channel;
    private String userName, password,  virtualHost, hostName;
    private int portNumber;

// "guest"/"guest" by default, limited to localhost connections

    public MessageProducer(String userName,String password, String virtualHost,String hostName,int portNumber) {
        this.userName=userName;
        this.password=password;
        this.virtualHost=virtualHost;
        this.hostName=hostName;
        this.portNumber = portNumber;

        this.factory = new ConnectionFactory();
        this.factory.setUsername(userName);
        this.factory.setPassword(password);
        this.factory.setVirtualHost(virtualHost);
        this.factory.setHost(hostName);
        this.factory.setPort(portNumber);

    }

    public boolean createConnection(){
        if(connection != null){
            try{
                this.connection = this.factory.newConnection();
            }catch (TimeoutException timeOut){
                timeOut.printStackTrace();
                return false;
            }catch (IOException iOexception){
                iOexception.printStackTrace();
                return false;
            }
        }

//        if(this.channel == null){
//            try{
//                this.channel = this.connection.createChannel();
//            }catch (IOException iOexception){
//                iOexception.printStackTrace();
//                return false;
//            }catch (NullPointerException exc){
//                exc.printStackTrace();
//                return false;
//            }
//
//
//        }

        return true;
    }

    public boolean closeConnection(){
        try{
//            if(this.channel != null)this.channel.close();
            if(this.connection != null)this.connection.close();
        }
//        catch (TimeoutException timeOut){
//            timeOut.printStackTrace();
//            return false;
//        }
        catch (IOException iOexception){
            iOexception.printStackTrace();
            return false;
        }
        return true;
    }
}
