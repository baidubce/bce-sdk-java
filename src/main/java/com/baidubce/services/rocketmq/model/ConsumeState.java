package com.baidubce.services.rocketmq.model;

import lombok.Data;

@Data
public class ConsumeState {
    private String consumerGroupName;
    private MessageQueue messageQueue;
    private String clientId;
    private Long brokerOffset;
    private Long consumeOffset;
    private Long lastConsumeTime;
}
