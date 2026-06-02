package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.ConsumerGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListTopicSubscriptionsResponse extends RocketMQPagedResponse {
    private List<ConsumerGroup> consumerGroups;
}
