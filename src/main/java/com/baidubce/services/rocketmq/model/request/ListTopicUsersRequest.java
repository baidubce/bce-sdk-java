package com.baidubce.services.rocketmq.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListTopicUsersRequest extends RocketMQPagedRequest {
    private String clusterId;
    private String topicName;
}
