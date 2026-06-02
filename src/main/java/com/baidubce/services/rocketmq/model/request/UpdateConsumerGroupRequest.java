package com.baidubce.services.rocketmq.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateConsumerGroupRequest extends RocketMQBaseRequest {
    private String clusterId;
    private String consumerGroupName;
    private List<String> brokerNames;
    private Integer retryMaxTimes;
    private Boolean consumeBroadcastEnable;
    private Boolean consumeEnable;
}
