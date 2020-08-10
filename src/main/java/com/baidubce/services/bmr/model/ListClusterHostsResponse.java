package com.baidubce.services.bmr.model;

import com.baidubce.model.AbstractBceResponse;
import java.util.List;


/**
 * Represent the response of ListClusterHosts.
 * <p>
 * The response contains an array of BMR Instance Info objects.
 */
public class ListClusterHostsResponse extends AbstractBceResponse {

    private String clusterId;

    private List<HostInfo> hosts;


    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public List<HostInfo> getHosts() {
        return hosts;
    }

    public void setHosts(List<HostInfo> hosts) {
        this.hosts = hosts;
    }
}