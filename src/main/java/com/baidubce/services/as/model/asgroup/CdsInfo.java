package com.baidubce.services.as.model.asgroup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by xucongyang on 2018/06/25.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CdsInfo {
    private String volumeType;
    private int sizeInGB;
    private String snapshotId;
    private String snapshotName;

    public String getVolumeType() {
        return volumeType;
    }

    public void setVolumeType(String volumeType) {
        this.volumeType = volumeType;
    }

    public int getSizeInGB() {
        return sizeInGB;
    }

    public void setSizeInGB(int sizeInGB) {
        this.sizeInGB = sizeInGB;
    }

    public String getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    public String getSnapshotName() {
        return snapshotName;
    }

    public void setSnapshotName(String snapshotName) {
        this.snapshotName = snapshotName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CdsInfo{");
        sb.append("volumeType='").append(volumeType).append('\'');
        sb.append(", sizeInGB=").append(sizeInGB);
        sb.append(", snapshotId='").append(snapshotId).append('\'');
        sb.append(", snapshotName='").append(snapshotName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
