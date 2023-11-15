package com.baidubce.services.kafka.model.topic;

import lombok.Data;

import java.util.List;

@Data
public class TopicPartition {
    private String topicName;

    private int partitionId;

    private int leaderId;

    private List<Integer> replicas;

    private List<Integer> inSyncReplicas;

    private long minOffset;

    private long maxOffset;

    private long messageNum;

    private String lastUpdateTime;
}
