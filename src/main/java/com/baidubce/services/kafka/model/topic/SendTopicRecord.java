package com.baidubce.services.kafka.model.topic;

import lombok.Data;

@Data
public class SendTopicRecord {

    private String topicName;

    private int partitionId;

    private long offset;

    private long timestamp;

    private String key;

    private String value;

    private int serializedKeySize;

    private int serializedValueSize;
}
