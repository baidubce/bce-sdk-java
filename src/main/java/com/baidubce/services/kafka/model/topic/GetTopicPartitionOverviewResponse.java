package com.baidubce.services.kafka.model.topic;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class GetTopicPartitionOverviewResponse extends AbstractBceResponse {

    private TopicPartitionOverview overview;
}
