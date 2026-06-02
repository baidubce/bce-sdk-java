package com.baidubce.services.rocketmq.model;

import lombok.Data;

@Data
public class TopicQueue {
    private Integer queueId;
    private String brokerName;
    private Long minOffset;
    private Long maxOffset;
    private Long lastUpdateTime;
}
