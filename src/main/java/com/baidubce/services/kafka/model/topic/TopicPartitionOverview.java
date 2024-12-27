package com.baidubce.services.kafka.model.topic;

import lombok.Data;

@Data
public class TopicPartitionOverview {

    private long totalMessageNum;

    private String lastUpdateTime;
}
