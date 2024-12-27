package com.baidubce.services.kafka.model.topic;

import java.util.List;

import lombok.Data;

@Data
public class QueryTopicRecord {

    private String topicName;

    private int partitionId;

    private long offset;

    private long timestamp;

    private String key;

    private String value;

    private int size;

    private List<TopicRecordHeader> headers;
}
