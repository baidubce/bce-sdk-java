package com.baidubce.services.gaiadb.model;

import java.util.List;
import java.util.Map;

public class SnapshotPolicyListResponse extends GenericGaiadbResponse {
    private List<Map<String, Object>> policys;
    private List<Map<String, Object>> policies;
    private String marker;
    private Integer maxKeys;
    private String nextMarker;
    private Boolean isTruncated;

    public List<Map<String, Object>> getPolicys() {
        return policys;
    }

    public void setPolicys(List<Map<String, Object>> policys) {
        this.policys = policys;
    }

    public List<Map<String, Object>> getPolicies() {
        return policies;
    }

    public void setPolicies(List<Map<String, Object>> policies) {
        this.policies = policies;
    }

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
}
