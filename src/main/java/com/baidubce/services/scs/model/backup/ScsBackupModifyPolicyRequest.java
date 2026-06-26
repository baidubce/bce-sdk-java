package com.baidubce.services.scs.model.backup;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Request to manage scs backup.
 */
public class ScsBackupModifyPolicyRequest extends AbstractBceRequest {
    private String instanceId;
    private String backupTime;
    private String backupDays;
    private Integer expireDay;
    private String isEncrypt;
    private String isEnableBackupLog;
    private Boolean highFreqBackupEnable;
    private String highFreqBackupConfig;
    private List<String> highFreqList;

    @JsonIgnore
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getBackupTime() {
        return backupTime;
    }

    public void setBackupTime(String backupTime) {
        this.backupTime = backupTime;
    }

    public String getBackupDays() {
        return backupDays;
    }

    public void setBackupDays(String backupDays) {
        this.backupDays = backupDays;
    }

    public Integer getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(Integer expireDay) {
        this.expireDay = expireDay;
    }

    public String getIsEncrypt() {
        return isEncrypt;
    }

    public void setIsEncrypt(String isEncrypt) {
        this.isEncrypt = isEncrypt;
    }

    public String getIsEnableBackupLog() {
        return isEnableBackupLog;
    }

    public void setIsEnableBackupLog(String isEnableBackupLog) {
        this.isEnableBackupLog = isEnableBackupLog;
    }

    public Boolean getHighFreqBackupEnable() {
        return highFreqBackupEnable;
    }

    public void setHighFreqBackupEnable(Boolean highFreqBackupEnable) {
        this.highFreqBackupEnable = highFreqBackupEnable;
    }

    public String getHighFreqBackupConfig() {
        return highFreqBackupConfig;
    }

    public void setHighFreqBackupConfig(String highFreqBackupConfig) {
        this.highFreqBackupConfig = highFreqBackupConfig;
    }

    public List<String> getHighFreqList() {
        return highFreqList;
    }

    public void setHighFreqList(List<String> highFreqList) {
        this.highFreqList = highFreqList;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
