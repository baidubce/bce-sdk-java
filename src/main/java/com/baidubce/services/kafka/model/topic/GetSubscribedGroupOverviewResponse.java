package com.baidubce.services.kafka.model.topic;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class GetSubscribedGroupOverviewResponse extends AbstractBceResponse {

    private SubscribedGroupOverview overview;
}
