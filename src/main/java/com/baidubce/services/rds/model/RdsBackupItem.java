package com.baidubce.services.rds.model;

/**
 * The item of rds backup list
 */
public class RdsBackupItem {
    private String backupId;
    private Integer backupSize;
    private String backupType;
    private String backupStatus;
    private String backupStartTime;
    private String backupEndTime;

    public String getBackupId() {
        return backupId;
    }

    public void setBackupId(String backupId) {
        this.backupId = backupId;
    }

    public Integer getBackupSize() {
        return backupSize;
    }

    public void setBackupSize(Integer backupSize) {
        this.backupSize = backupSize;
    }

    public String getBackupType() {
        return backupType;
    }

    public void setBackupType(String backupType) {
        this.backupType = backupType;
    }

    public String getBackupStatus() {
        return backupStatus;
    }

    public void setBackupStatus(String backupStatus) {
        this.backupStatus = backupStatus;
    }

    public String getBackupStartTime() {
        return backupStartTime;
    }

    public void setBackupStartTime(String backupStartTime) {
        this.backupStartTime = backupStartTime;
    }

    public String getBackupEndTime() {
        return backupEndTime;
    }

    public void setBackupEndTime(String backupEndTime) {
        this.backupEndTime = backupEndTime;
    }
}
