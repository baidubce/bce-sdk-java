package com.baidubce.services.gaiadb.model;

import java.util.List;
import java.util.Map;

public class ClusterListResponse extends GenericGaiadbResponse {
    private String marker;
    private Integer maxKeys;
    private String nextMarker;
    private Boolean isTruncated;
    private List<Map<String, Object>> clusters;

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

    public String getNextMarker() {
        return nextMarker;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    public Boolean getIsTruncated() {
        return isTruncated;
    }

    public void setIsTruncated(Boolean truncated) {
        isTruncated = truncated;
    }

    public List<Map<String, Object>> getClusters() {
        return clusters;
    }

    public void setClusters(List<Map<String, Object>> clusters) {
        this.clusters = clusters;
    }
}
