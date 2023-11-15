package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request of modify backup
 */
public class RdsModifyBackupRequest extends AbstractBceRequest {

    private String instanceId;
    private String backupDays;
    private String backupTime;
    private Boolean persistent;
    private Integer expireInDays;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getBackupDays() {
        return backupDays;
    }

    public void setBackupDays(String backupDays) {
        this.backupDays = backupDays;
    }

    public String getBackupTime() {
        return backupTime;
    }

    public void setBackupTime(String backupTime) {
        this.backupTime = backupTime;
    }

    public Boolean getPersistent() {
        return persistent;
    }

    public void setPersistent(Boolean persistent) {
        this.persistent = persistent;
    }

    public Integer getExpireInDays() {
        return expireInDays;
    }

    public void setExpireInDays(Integer expireInDays) {
        this.expireInDays = expireInDays;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
