package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * The response of rds backup list
 */
public class RdsGetBackupListResponse extends AbstractBceResponse {

    private String marker;
    private Integer maxKeys;
    private Boolean isTruncated;
    private String nextMarker;
    private List<RdsBackupItem> backups;

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

    public String getNextMarker() {
        return nextMarker;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    public List<RdsBackupItem> getBackups() {
        return backups;
    }

    public void setBackups(List<RdsBackupItem> backups) {
        this.backups = backups;
    }
}
