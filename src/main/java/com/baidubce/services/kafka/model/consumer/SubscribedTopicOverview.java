package com.baidubce.services.kafka.model.consumer;

import lombok.Data;

@Data
public class SubscribedTopicOverview {

    private int subscribedTopicNum;

    private String lastConsumeTime;
}
