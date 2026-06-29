package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Request for modifying the automatic backup policy of a MongoDB instance.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbBackupPolicyRequest extends GenericMongodbRequest {
    private String dbInstanceId;

    private String preferredBackupPeriod;

    private String preferredBackupTime;

    private String autoBackupEnable;

    private Integer enableIncrementBackup;

    // backup retention days, default 7
    private Integer backupRetentionPeriod;

    // incremental backup retention days, default 7
    private Integer incrBackupRetentionPeriod;

    private String backupMethod;

    private Boolean dataBackupCopyEnable;

    private List<String> dataBackupCopyStoragesRegions;

    public String getDbInstanceId() {
        return dbInstanceId;
    }

    public void setDbInstanceId(String dbInstanceId) {
        this.dbInstanceId = dbInstanceId;
    }

    public String getPreferredBackupPeriod() {
        return preferredBackupPeriod;
    }

    public void setPreferredBackupPeriod(String preferredBackupPeriod) {
        this.preferredBackupPeriod = preferredBackupPeriod;
    }

    public String getPreferredBackupTime() {
        return preferredBackupTime;
    }

    public void setPreferredBackupTime(String preferredBackupTime) {
        this.preferredBackupTime = preferredBackupTime;
    }

    public String getAutoBackupEnable() {
        return autoBackupEnable;
    }

    public void setAutoBackupEnable(String autoBackupEnable) {
        this.autoBackupEnable = autoBackupEnable;
    }

    public Integer getEnableIncrementBackup() {
        return enableIncrementBackup;
    }

    public void setEnableIncrementBackup(Integer enableIncrementBackup) {
        this.enableIncrementBackup = enableIncrementBackup;
    }

    public Integer getBackupRetentionPeriod() {
        return backupRetentionPeriod;
    }

    public void setBackupRetentionPeriod(Integer backupRetentionPeriod) {
        this.backupRetentionPeriod = backupRetentionPeriod;
    }

    public Integer getIncrBackupRetentionPeriod() {
        return incrBackupRetentionPeriod;
    }

    public void setIncrBackupRetentionPeriod(Integer incrBackupRetentionPeriod) {
        this.incrBackupRetentionPeriod = incrBackupRetentionPeriod;
    }

    public String getBackupMethod() {
        return backupMethod;
    }

    public void setBackupMethod(String backupMethod) {
        this.backupMethod = backupMethod;
    }

    public Boolean getDataBackupCopyEnable() {
        return dataBackupCopyEnable;
    }

    public void setDataBackupCopyEnable(Boolean dataBackupCopyEnable) {
        this.dataBackupCopyEnable = dataBackupCopyEnable;
    }

    public List<String> getDataBackupCopyStoragesRegions() {
        return dataBackupCopyStoragesRegions;
    }

    public void setDataBackupCopyStoragesRegions(List<String> dataBackupCopyStoragesRegions) {
        this.dataBackupCopyStoragesRegions = dataBackupCopyStoragesRegions;
    }
}
