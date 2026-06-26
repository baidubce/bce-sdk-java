package com.baidubce.services.scs.model.backup;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Request to manage scs backup.
 */
public class ScsBackupRequest extends AbstractBceRequest {

    private String instanceId;
    private String batchId;
    private String backupRecordId;
    private String comment;
    private ScsBackupPolicy backupPolicy;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBackupRecordId() {
        return backupRecordId;
    }

    public void setBackupRecordId(String backupRecordId) {
        this.backupRecordId = backupRecordId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ScsBackupPolicy getBackupPolicy() {
        return backupPolicy;
    }

    public void setBackupPolicy(ScsBackupPolicy backupPolicy) {
        this.backupPolicy = backupPolicy;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
