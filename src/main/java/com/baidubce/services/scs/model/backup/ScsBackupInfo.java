package com.baidubce.services.scs.model.backup;

import java.util.List;

/**
 * Scs backup info.
 */
public class ScsBackupInfo {

    private String backupType;
    private String comment;
    private String startTime;
    private String recoverable;
    private String batchId;
    private List<ScsBackupRecord> records;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getRecoverable() {
        return recoverable;
    }

    public void setRecoverable(String recoverable) {
        this.recoverable = recoverable;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public List<ScsBackupRecord> getRecords() {
        return records;
    }

    public void setRecords(List<ScsBackupRecord> records) {
        this.records = records;
    }
}
