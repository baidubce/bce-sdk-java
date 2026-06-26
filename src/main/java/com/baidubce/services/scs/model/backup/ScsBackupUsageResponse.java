package com.baidubce.services.scs.model.backup;

import com.baidubce.model.AbstractBceResponse;

/**
 * Response of scs backup usage.
 */
public class ScsBackupUsageResponse extends AbstractBceResponse {

    private Long logicalLogBackupBillingSizeBytes;
    private Long snapshotDataBackupSizeBytes;
    private Long physicalDataBackupSizeBytes;
    private Long logicalLogBackupSizeBytes;
    private Long logicalDataBackupSizeBytes;
    private Long physicalDataBackupBillingSizeBytes;
    private Long logicalDataBackupBillingSizeBytes;
    private Long physicalLogBackupSizeBytes;
    private Long physicalLogBackupBillingSizeBytes;
    private Long snapshotDataBackupBillingSizeBytes;
    private String dataType;
    private String clusterID;
    private String appID;

    public Long getLogicalLogBackupBillingSizeBytes() {
        return logicalLogBackupBillingSizeBytes;
    }

    public void setLogicalLogBackupBillingSizeBytes(Long logicalLogBackupBillingSizeBytes) {
        this.logicalLogBackupBillingSizeBytes = logicalLogBackupBillingSizeBytes;
    }

    public Long getSnapshotDataBackupSizeBytes() {
        return snapshotDataBackupSizeBytes;
    }

    public void setSnapshotDataBackupSizeBytes(Long snapshotDataBackupSizeBytes) {
        this.snapshotDataBackupSizeBytes = snapshotDataBackupSizeBytes;
    }

    public Long getPhysicalDataBackupSizeBytes() {
        return physicalDataBackupSizeBytes;
    }

    public void setPhysicalDataBackupSizeBytes(Long physicalDataBackupSizeBytes) {
        this.physicalDataBackupSizeBytes = physicalDataBackupSizeBytes;
    }

    public Long getLogicalLogBackupSizeBytes() {
        return logicalLogBackupSizeBytes;
    }

    public void setLogicalLogBackupSizeBytes(Long logicalLogBackupSizeBytes) {
        this.logicalLogBackupSizeBytes = logicalLogBackupSizeBytes;
    }

    public Long getLogicalDataBackupSizeBytes() {
        return logicalDataBackupSizeBytes;
    }

    public void setLogicalDataBackupSizeBytes(Long logicalDataBackupSizeBytes) {
        this.logicalDataBackupSizeBytes = logicalDataBackupSizeBytes;
    }

    public Long getPhysicalDataBackupBillingSizeBytes() {
        return physicalDataBackupBillingSizeBytes;
    }

    public void setPhysicalDataBackupBillingSizeBytes(Long physicalDataBackupBillingSizeBytes) {
        this.physicalDataBackupBillingSizeBytes = physicalDataBackupBillingSizeBytes;
    }

    public Long getLogicalDataBackupBillingSizeBytes() {
        return logicalDataBackupBillingSizeBytes;
    }

    public void setLogicalDataBackupBillingSizeBytes(Long logicalDataBackupBillingSizeBytes) {
        this.logicalDataBackupBillingSizeBytes = logicalDataBackupBillingSizeBytes;
    }

    public Long getPhysicalLogBackupSizeBytes() {
        return physicalLogBackupSizeBytes;
    }

    public void setPhysicalLogBackupSizeBytes(Long physicalLogBackupSizeBytes) {
        this.physicalLogBackupSizeBytes = physicalLogBackupSizeBytes;
    }

    public Long getPhysicalLogBackupBillingSizeBytes() {
        return physicalLogBackupBillingSizeBytes;
    }

    public void setPhysicalLogBackupBillingSizeBytes(Long physicalLogBackupBillingSizeBytes) {
        this.physicalLogBackupBillingSizeBytes = physicalLogBackupBillingSizeBytes;
    }

    public Long getSnapshotDataBackupBillingSizeBytes() {
        return snapshotDataBackupBillingSizeBytes;
    }

    public void setSnapshotDataBackupBillingSizeBytes(Long snapshotDataBackupBillingSizeBytes) {
        this.snapshotDataBackupBillingSizeBytes = snapshotDataBackupBillingSizeBytes;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getClusterID() {
        return clusterID;
    }

    public void setClusterID(String clusterID) {
        this.clusterID = clusterID;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }
}
