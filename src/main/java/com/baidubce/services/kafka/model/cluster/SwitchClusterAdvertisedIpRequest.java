/*
 * Copyright (C) 2024 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.kafka.model.cluster;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * Switches the cross-VPC advertised-IP feature for a Kafka cluster.
 *
 * <p>Endpoint: {@code PUT /v2/clusters/{clusterId}/advertised-ips/switch}</p>
 */
@Data
public class SwitchClusterAdvertisedIpRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private Boolean advertisedIpEnabled;

    @Override
    public SwitchClusterAdvertisedIpRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
