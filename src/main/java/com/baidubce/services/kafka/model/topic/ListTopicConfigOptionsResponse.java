package com.baidubce.services.kafka.model.topic;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class ListTopicConfigOptionsResponse extends AbstractBceResponse {

    private List<TopicConfigOption> topicConfigs;
}
