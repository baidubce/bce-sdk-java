package com.baidubce.services.kafka.model.topic;

import lombok.Data;

@Data
public class GroupTopicPartition {

    private String topicName;

    private int partitionId;

    private String consumerId;

    private long maxOffset;

    private long committedOffset;

    private long lag;

    private String lastConsumeTime;
}
