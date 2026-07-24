package com.baidubce.services.kafka.model.cluster;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UnifyClusterEndpointRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    @JsonIgnore
    private String clusterId;

    private String actionId;

    @Override
    public UnifyClusterEndpointRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
