package com.baidubce.services.rocketmq.model;

import lombok.Data;

@Data
public class TopicConfig {
    private String brokerName;
    private int readQueueNum;
    private int writeQueueNum;
}
