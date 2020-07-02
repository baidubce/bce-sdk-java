package com.baidubce.services.bmr.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Provides option for desribing a BMR cluster Ambari request. The essential option is cluster ID.
 */
public class AmbariRequest extends AbstractBceRequest {
    private String clusterId;

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    /**
     * Configure the cluster ID for the request.
     *
     * @param clusterId The ID of BMR cluster.
     *
     * @return AmbariRequest
     */
    public AmbariRequest withClusterId(String clusterId) {
        this.setClusterId(clusterId);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     *
     * @return AmbariRequest
     */
    public AmbariRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
