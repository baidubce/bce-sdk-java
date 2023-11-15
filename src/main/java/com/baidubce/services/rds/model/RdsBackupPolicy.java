package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Rds backup policy
 */
public class RdsBackupPolicy {
    private String backupDays;
    private String backupTime;
    private Boolean persistent;
    private Integer expireInDays;
    @JsonProperty("freeSpaceInGB")
    private Integer freeSpace;

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

    public Integer getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(Integer freeSpace) {
        this.freeSpace = freeSpace;
    }
}
