package com.baidubce.services.kafka.model.cluster;

import lombok.Data;

@Data
public class AccessEndpoint {

    private String securityProtocol;

    private String endpoints;

    private String network;
}
