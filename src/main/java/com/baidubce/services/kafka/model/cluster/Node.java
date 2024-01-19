package com.baidubce.services.kafka.model.cluster;

import lombok.Data;

@Data
public class Node {

    private String brokerId;

    private String host;

    private String nodeId;

    private String state;

    private String publicIp;

    private String internalIp;
}
