package com.baidubce.services.rocketmq.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryMessageListRequest extends RocketMQPagedRequest {
    private String clusterId;
    private String topicName;
    private String messageId;
    private String messageKey;
    private Long beginTime;
    private Long endTime;
}
