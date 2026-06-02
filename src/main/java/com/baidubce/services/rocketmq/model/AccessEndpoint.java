package com.baidubce.services.rocketmq.model;

import lombok.Data;

@Data
public class AccessEndpoint {
    private String communicationProtocol;
    private String endpoint;
}
