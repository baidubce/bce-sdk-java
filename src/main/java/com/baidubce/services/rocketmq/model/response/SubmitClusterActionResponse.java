package com.baidubce.services.rocketmq.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SubmitClusterActionResponse extends RocketMQBaseResponse {
    private String actionId;
}
