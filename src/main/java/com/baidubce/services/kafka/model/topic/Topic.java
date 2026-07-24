package com.baidubce.services.kafka.model.topic;

import lombok.Data;

@Data
public class Topic {
    private String topicName;

    private String createTime;

    private Boolean readOnly;

    private Integer partitionNum;

    private Integer replicaNum;
}
