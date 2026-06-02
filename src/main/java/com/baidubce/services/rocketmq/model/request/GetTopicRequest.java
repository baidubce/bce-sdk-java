package com.baidubce.services.rocketmq.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetTopicRequest extends RocketMQBaseRequest {
    private String clusterId;
    private String topicName;
}
