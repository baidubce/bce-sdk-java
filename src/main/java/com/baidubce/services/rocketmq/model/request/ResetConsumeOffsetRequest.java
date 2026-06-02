package com.baidubce.services.rocketmq.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResetConsumeOffsetRequest extends RocketMQBaseRequest {
    private String clusterId;
    private String consumerGroupName;
    private String topicName;
    private Long timestamp;
}
