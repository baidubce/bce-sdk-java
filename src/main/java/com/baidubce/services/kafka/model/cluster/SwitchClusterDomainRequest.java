/*
 * Copyright (C) 2024 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.kafka.model.cluster;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * Switches the domain-access feature for a Kafka cluster.
 *
 * <p>Endpoint: {@code PUT /v2/clusters/{clusterId}/domains/switch}</p>
 */
@Data
public class SwitchClusterDomainRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private Boolean domainEnabled;

    @Override
    public SwitchClusterDomainRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
