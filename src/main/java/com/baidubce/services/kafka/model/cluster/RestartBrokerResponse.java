package com.baidubce.services.kafka.model.cluster;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class RestartBrokerResponse extends AbstractBceResponse {

    private String clusterId;

    private String nodeId;

    private String jobId;
}
