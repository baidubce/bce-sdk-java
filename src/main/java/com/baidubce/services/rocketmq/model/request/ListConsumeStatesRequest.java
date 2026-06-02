package com.baidubce.services.rocketmq.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListConsumeStatesRequest extends RocketMQPagedRequest {
    private String clusterId;
    private String consumerGroupName;
    private String topicName;
    private List<String> brokerNames;
}
