package com.baidubce.services.gaiadb.model;

public class ClusterListRequest extends GenericGaiadbRequest {
    private String marker;
    private Integer maxKeys;
    private String clusterIpList;
    private String clusterIds;

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public Integer getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    public String getClusterIpList() {
        return clusterIpList;
    }

    public void setClusterIpList(String clusterIpList) {
        this.clusterIpList = clusterIpList;
    }

    public String getClusterIds() {
        return clusterIds;
    }

    public void setClusterIds(String clusterIds) {
        this.clusterIds = clusterIds;
    }
}
