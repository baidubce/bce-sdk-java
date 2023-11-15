package com.baidubce.services.kafka.model.topic;

import lombok.Data;

import java.util.Map;

@Data
public class TopicDetail {

    private String topicName;

    private int partitionNum;

    private int replicationFactor;

    private double brokersSkewed;

    private double brokersLeaderSkewed;

    private double brokersSpread;

    private double preferredReplicas;

    private double underReplicated;

    private Map<String, String> otherConfigs;
}
