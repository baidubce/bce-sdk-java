package com.baidubce.services.rocketmq.model;

import lombok.Data;

import java.util.List;

@Data
public class Topic {
    private String topicName;
    private List<Broker> brokers;
    private Integer permission;
    private Integer readQueueSum;
    private Integer writeQueueSum;
}
