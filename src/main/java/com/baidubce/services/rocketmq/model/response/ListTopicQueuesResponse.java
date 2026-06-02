package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.TopicQueue;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListTopicQueuesResponse extends RocketMQPagedResponse {
    private List<TopicQueue> topicQueues;
}
