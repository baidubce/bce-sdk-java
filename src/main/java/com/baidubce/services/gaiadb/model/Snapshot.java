package com.baidubce.services.gaiadb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Snapshot information of a GaiaDB cluster.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Snapshot {

    private String snapshotId;
    private String appId;
    private Long snapshotSizeInBytes;
    private String snapshotType;
    private String snapshotStatus;
    private String snapshotStartTime;
    private String snapshotEndTime;
    private String snapshotDataTime;
    private String pit;

    public String getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getSnapshotSizeInBytes() {
        return snapshotSizeInBytes;
    }

    public void setSnapshotSizeInBytes(Long snapshotSizeInBytes) {
        this.snapshotSizeInBytes = snapshotSizeInBytes;
    }

    public String getSnapshotType() {
        return snapshotType;
    }

    public void setSnapshotType(String snapshotType) {
        this.snapshotType = snapshotType;
    }

    public String getSnapshotStatus() {
        return snapshotStatus;
    }

    public void setSnapshotStatus(String snapshotStatus) {
        this.snapshotStatus = snapshotStatus;
    }

    public String getSnapshotStartTime() {
        return snapshotStartTime;
    }

    public void setSnapshotStartTime(String snapshotStartTime) {
        this.snapshotStartTime = snapshotStartTime;
    }

    public String getSnapshotEndTime() {
        return snapshotEndTime;
    }

    public void setSnapshotEndTime(String snapshotEndTime) {
        this.snapshotEndTime = snapshotEndTime;
    }

    public String getSnapshotDataTime() {
        return snapshotDataTime;
    }

    public void setSnapshotDataTime(String snapshotDataTime) {
        this.snapshotDataTime = snapshotDataTime;
    }

    public String getPit() {
        return pit;
    }

    public void setPit(String pit) {
        this.pit = pit;
    }
}
