package com.baidubce.services.rocketmq.model;

import lombok.Data;

@Data
public class Message {
    String msgId;
    String topicName;
    String tag;
    String key;
    Long storeTime;
    byte[] body;

    @Data
    public static class MessageInner {
        String msgId;
        String topicName;
        String tag;
        String key;
        Long storeTime;
        String base64Body;
    }
}
