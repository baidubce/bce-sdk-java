package com.baidubce.services.rocketmq.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListActionOperationsRequest extends RocketMQPagedRequest {
    private String clusterId;
    private String actionId;
}
