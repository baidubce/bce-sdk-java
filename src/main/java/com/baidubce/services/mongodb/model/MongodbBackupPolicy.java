package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Automatic backup policy of a MongoDB instance.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbBackupPolicy extends GenericMongodbResponse {
    private String autoBackupEnable;

    private int backupRetentionPeriod;

    private int incrBackupRetentionPeriod;

    private String preferredBackupPeriod;

    private String preferredBackupTime;

    private int enableIncrementBackup;

    private String backupMethod;

    private boolean dataBackupCopyEnable;

    private List<String> dataBackupCopyStoragesRegions;

    public String getAutoBackupEnable() {
        return autoBackupEnable;
    }

    public void setAutoBackupEnable(String autoBackupEnable) {
        this.autoBackupEnable = autoBackupEnable;
    }

    public int getBackupRetentionPeriod() {
        return backupRetentionPeriod;
    }

    public void setBackupRetentionPeriod(int backupRetentionPeriod) {
        this.backupRetentionPeriod = backupRetentionPeriod;
    }

    public int getIncrBackupRetentionPeriod() {
        return incrBackupRetentionPeriod;
    }

    public void setIncrBackupRetentionPeriod(int incrBackupRetentionPeriod) {
        this.incrBackupRetentionPeriod = incrBackupRetentionPeriod;
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

    public int getEnableIncrementBackup() {
        return enableIncrementBackup;
    }

    public void setEnableIncrementBackup(int enableIncrementBackup) {
        this.enableIncrementBackup = enableIncrementBackup;
    }

    public String getBackupMethod() {
        return backupMethod;
    }

    public void setBackupMethod(String backupMethod) {
        this.backupMethod = backupMethod;
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
}
