package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

/**
 * The request of modify backup
 */
public class RdsModifyBackupRequest extends AbstractBceRequest {

    private String instanceId;
    private String backupDays;
    private String backupTime;
    private Boolean persistent;
    private Integer expireInDays;
    private Integer logBackupRetainDays;
    private boolean incrementalDataBackupEnable;
    private Integer incrementalDataBackupInterval;
    private Integer latestDataBackupRetainDays;
    private Integer retentionPeriod;
    private String timeUnit;
    private String dataBackupType;
    private boolean dataBackupCopyEnable;
    private List<String> dataBackupCopyStoragesRegions;
    private Integer dataBackupCopyRetainDays;
    private String binlogSizePercent;
    private String binlogExpiredHour;


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

    public Integer getLogBackupRetainDays() {
        return logBackupRetainDays;
    }

    public void setLogBackupRetainDays(Integer logBackupRetainDays) {
        this.logBackupRetainDays = logBackupRetainDays;
    }

    public boolean isIncrementalDataBackupEnable() {
        return incrementalDataBackupEnable;
    }

    public void setIncrementalDataBackupEnable(boolean incrementalDataBackupEnable) {
        this.incrementalDataBackupEnable = incrementalDataBackupEnable;
    }

    public Integer getIncrementalDataBackupInterval() {
        return incrementalDataBackupInterval;
    }

    public void setIncrementalDataBackupInterval(Integer incrementalDataBackupInterval) {
        this.incrementalDataBackupInterval = incrementalDataBackupInterval;
    }

    public Integer getLatestDataBackupRetainDays() {
        return latestDataBackupRetainDays;
    }

    public void setLatestDataBackupRetainDays(Integer latestDataBackupRetainDays) {
        this.latestDataBackupRetainDays = latestDataBackupRetainDays;
    }

    public Integer getRetentionPeriod() {
        return retentionPeriod;
    }

    public void setRetentionPeriod(Integer retentionPeriod) {
        this.retentionPeriod = retentionPeriod;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getDataBackupType() {
        return dataBackupType;
    }

    public void setDataBackupType(String dataBackupType) {
        this.dataBackupType = dataBackupType;
    }

    public boolean getDataBackupCopyEnable() {
        return dataBackupCopyEnable;
    }

    public void setDataBackupCopyEnable(boolean dataBackupCopyEnable) {
        this.dataBackupCopyEnable = dataBackupCopyEnable;
    }

    public List<String> getDataBackupCopyStoragesRegions() {
        return dataBackupCopyStoragesRegions;
    }

    public void setDataBackupCopyStoragesRegions(List<String> dataBackupCopyStoragesRegions) {
        this.dataBackupCopyStoragesRegions = dataBackupCopyStoragesRegions;
    }

    public Integer getDataBackupCopyRetainDays() {
        return dataBackupCopyRetainDays;
    }

    public void setDataBackupCopyRetainDays(Integer dataBackupCopyRetainDays) {
        this.dataBackupCopyRetainDays = dataBackupCopyRetainDays;
    }

    public String getBinlogSizePercent() {
        return binlogSizePercent;
    }

    public void setBinlogSizePercent(String binlogSizePercent) {
        this.binlogSizePercent = binlogSizePercent;
    }

    public String getBinlogExpiredHour() {
        return binlogExpiredHour;
    }

    public void setBinlogExpiredHour(String binlogExpiredHour) {
        this.binlogExpiredHour = binlogExpiredHour;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
