package com.baidubce.services.scs.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Instance list response
 */
public class InstanceListResponse extends AbstractBceResponse {

    private String nextMarker;
    private String marker;
    private Integer maxKeys;
    private Boolean isTruncated;
    private List<ScsInstance> instances = new ArrayList<ScsInstance>();

    public String getNextMarker() {
        return nextMarker;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
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

    public Boolean getIsTruncated() {
        return isTruncated;
    }

    public void setIsTruncated(Boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    public List<ScsInstance> getInstances() {
        return instances;
    }

    public void setInstances(List<ScsInstance> instances) {
        this.instances = instances;
    }

    @Override
    public String toString() {
        return "InstanceListResponse{" +
                "nextMarker='" + nextMarker + '\'' +
                ", marker='" + marker + '\'' +
                ", maxKeys=" + maxKeys +
                ", isTruncated=" + isTruncated +
                ", instances=" + instances +
                '}';
    }
}