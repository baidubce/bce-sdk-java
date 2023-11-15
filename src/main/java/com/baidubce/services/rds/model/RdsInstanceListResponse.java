package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Response of rds instance list
 */
public class RdsInstanceListResponse extends AbstractBceResponse {

    private String marker;
    private Integer maxKeys;
    private boolean isTruncated;
    private String nextMarker;
    private List<RdsInstance> instances = new ArrayList<RdsInstance>();

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

    public boolean getIsTruncated() {
        return isTruncated;
    }

    public void setIsTruncated(boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    public String getNextMarker() {
        return nextMarker;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    public List<RdsInstance> getInstances() {
        return instances;
    }

    public void setInstances(List<RdsInstance> instances) {
        this.instances = instances;
    }
}
