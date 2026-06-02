package com.baidubce.services.rocketmq.model;

import lombok.Data;

@Data
public class MessageQueue {
    private String brokerName;
    private String topicName;
    private Integer queueId;
}
