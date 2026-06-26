package com.baidubce.services.scs.model.backup;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Request to manage scs backup.
 */
public class ScsBackupDetailRequest extends AbstractBceRequest {

    private String instanceId;
    private String backupRecordId;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getBackupRecordId() {
        return backupRecordId;
    }

    public void setBackupRecordId(String backupRecordId) {
        this.backupRecordId = backupRecordId;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
