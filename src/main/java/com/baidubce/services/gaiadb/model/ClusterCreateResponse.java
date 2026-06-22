package com.baidubce.services.gaiadb.model;

import java.util.List;

public class ClusterCreateResponse extends GenericGaiadbResponse {
    private String orderId;
    private List<String> clusterIds;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<String> getClusterIds() {
        return clusterIds;
    }

    public void setClusterIds(List<String> clusterIds) {
        this.clusterIds = clusterIds;
    }
}
