package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsUpdateEncryptPolicyReq extends AbstractBceRequest {

    private String instanceId;
    private RdsUpdateEncryptPolicyRequest backupPolicy;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public RdsUpdateEncryptPolicyRequest getBackupPolicy() {
        return backupPolicy;
    }

    public void setBackupPolicy(RdsUpdateEncryptPolicyRequest backupPolicy) {
        this.backupPolicy = backupPolicy;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
