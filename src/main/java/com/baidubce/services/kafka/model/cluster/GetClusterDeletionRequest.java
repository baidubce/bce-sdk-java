package com.baidubce.services.kafka.model.cluster;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

@Data
public class GetClusterDeletionRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    @Override
    public GetClusterDeletionRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
