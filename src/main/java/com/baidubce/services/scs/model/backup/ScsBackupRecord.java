package com.baidubce.services.scs.model.backup;

/**
 * Scs backup record.
 */
public class ScsBackupRecord {

    private String backupRecordId;
    private String backupStatus;
    private Long duration;
    private Long objectSize;
    private String shardName;
    private String startTime;
    private String backupType;
    private String comment;

    public String getBackupRecordId() {
        return backupRecordId;
    }

    public void setBackupRecordId(String backupRecordId) {
        this.backupRecordId = backupRecordId;
    }

    public String getBackupStatus() {
        return backupStatus;
    }

    public void setBackupStatus(String backupStatus) {
        this.backupStatus = backupStatus;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getObjectSize() {
        return objectSize;
    }

    public void setObjectSize(Long objectSize) {
        this.objectSize = objectSize;
    }

    public String getShardName() {
        return shardName;
    }

    public void setShardName(String shardName) {
        this.shardName = shardName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getBackupType() {
        return backupType;
    }

    public void setBackupType(String backupType) {
        this.backupType = backupType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
