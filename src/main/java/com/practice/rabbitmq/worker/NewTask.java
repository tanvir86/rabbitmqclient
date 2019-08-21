package com.practice.rabbitmq.worker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Scanner;

public class NewTask {
    private static final String TASK_QUEUE_NAME = "testQueue3";//"testQueue1";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.26.147");
//        factory.setUsername("admin");
//        factory.setPassword("rabidmq@16263");
//        factory.setVirtualHost("/");
        factory.setUsername("telenorTest");
        factory.setPassword("123321");
        factory.setVirtualHost("telenorTest");
        factory.setPort(5672);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

            Scanner scanner = new Scanner(System.in);
            int i=0;
            while(i++<10){
                System.out.println("Write new Message:(write any one word)");
                String message = scanner.next();
//                message = getMessageText();
//                message = getComputeRequestMessage();
                message = getComputeResponseMessage(message);
                channel.basicPublish("", TASK_QUEUE_NAME,
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + message + "'");
            }
        }
    }
    public static String getComputeResponseMessage(String id) throws Exception{
        UserExtensionLogComputeResponse userExtensionLogComputeResponse = new UserExtensionLogComputeResponse();

        userExtensionLogComputeResponse.setId(Long.parseLong(id));
        userExtensionLogComputeResponse.setAlarm(1);
        userExtensionLogComputeResponse.setCallsLanded(7);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(userExtensionLogComputeResponse);
    }
    public static String getComputeRequestMessage() throws Exception{
        UserExtensionLogComputeRequest userExtensionLogComputeRequest = new UserExtensionLogComputeRequest("7001",LocalDateTime.now().minusHours(2).toString(),
                                                                                        LocalDateTime.now().minusMinutes(10).toString());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(userExtensionLogComputeRequest);
    }

    public static String getMessageText() throws Exception {
        QueueLog queueLog = new QueueLog("7001","leave",LocalDateTime.now().toString());
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(queueLog);  //writerWithDefaultPrettyPrinter()
    }

    public static class UserExtensionLogComputeRequest{
        private String extension;
        private String startTime;
        private String endTime;

        public UserExtensionLogComputeRequest(String extension, String startTime, String endTime) {
            this.extension = extension;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }

    public static class QueueLog{
        private String extension;
        private String status;
        private String eventTime;

        public QueueLog(String extension, String status, String eventTime) {
            this.extension = extension;
            this.status = status;
            this.eventTime = eventTime;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getEventTime() {
            return eventTime;
        }

        public void setEventTime(String eventTime) {
            this.eventTime = eventTime;
        }
    }

    public static class UserExtensionLogComputeResponse {
        private Long id;
        private String hms;
        private int duration;
        private int callsLanded;
        private int callsTaken;
        private int talkTime;
        private int alarm;
        private int totalRingTime;

        public UserExtensionLogComputeResponse() {
        }

        public  UserExtensionLogComputeResponse(Long id,String hms, int duration, int callsLanded, int callsTaken, int talkTime, int alarm, int totalRingTime) {
            this.id = id;
            this.hms = hms;
            this.duration = duration;
            this.callsLanded = callsLanded;
            this.callsTaken = callsTaken;
            this.talkTime = talkTime;
            this.alarm = alarm;
            this.totalRingTime = totalRingTime;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getHms() {
            return hms;
        }

        public void setHms(String hms) {
            this.hms = hms;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getCallsLanded() {
            return callsLanded;
        }

        public void setCallsLanded(int callsLanded) {
            this.callsLanded = callsLanded;
        }

        public int getCallsTaken() {
            return callsTaken;
        }

        public void setCallsTaken(int callsTaken) {
            this.callsTaken = callsTaken;
        }

        public int getTalkTime() {
            return talkTime;
        }

        public void setTalkTime(int talkTime) {
            this.talkTime = talkTime;
        }

        public int getAlarm() {
            return alarm;
        }

        public void setAlarm(int alarm) {
            this.alarm = alarm;
        }

        public int getTotalRingTime() {
            return totalRingTime;
        }

        public void setTotalRingTime(int totalRingTime) {
            this.totalRingTime = totalRingTime;
        }
    }
}
