package com.baidubce.services.bmr.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class ListClusterHostsRequest  extends AbstractBceRequest {

    private String clusterId;

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }


    /**
     * Configure the ID of the target cluster for the request.
     *
     * @param clusterId The ID of the target cluster.
     *
     * @return ListClusterHostsRequest
     */
    public ListClusterHostsRequest withClusterId(String clusterId) {
        this.setClusterId(clusterId);
        return this;
    }


    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     *
     * @return ListClusterHostsRequest
     */
    public ListClusterHostsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}