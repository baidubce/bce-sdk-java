package com.baidubce.services.rocketmq.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListClustersRequest extends RocketMQPagedRequest {
    private String clusterName;

    private String state;

    private String arch;

    private String rocketmqVersion;

    private String payment;

    private String tagKey;

    private String tagValue;
}
