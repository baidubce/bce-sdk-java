package com.baidubce.services.bmr.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 *
 *
 */
public class TemplateClusterRequest extends AbstractBceRequest {

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
     * @return GetClusterRequest
     */
    public TemplateClusterRequest withClusterId(String clusterId) {
        this.setClusterId(clusterId);
        return this;
    }

    @Override public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
