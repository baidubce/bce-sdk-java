package com.baidubce.services.rocketmq.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ExpandBrokerRequest extends RocketMQBaseRequest {
    private String clusterId;
    private List<String> couponIds;
    private Boolean isAutoPay;
    private Integer targetBrokerCount;
}
