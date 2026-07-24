package com.baidubce.services.kafka.model.consumer;

import lombok.Data;

@Data
public class SubscribedTopicOverview {

    private Integer subscribedTopicNum;

    private String lastConsumeTime;
}
