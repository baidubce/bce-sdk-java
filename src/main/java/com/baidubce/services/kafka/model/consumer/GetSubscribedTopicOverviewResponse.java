package com.baidubce.services.kafka.model.consumer;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class GetSubscribedTopicOverviewResponse extends AbstractBceResponse {

    private SubscribedTopicOverview overview;
}
