package com.baidubce.services.rocketmq.model;

import lombok.Data;

@Data
public class BrokerNode {
    private String brokerName;
    private Integer brokerId;
    private String brokerRole;
    private String brokerServerId;
    private String status;
    private String nodeId;
    private String zoneName;
}
