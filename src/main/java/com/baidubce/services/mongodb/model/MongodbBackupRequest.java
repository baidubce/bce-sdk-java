package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Request for creating or modifying a MongoDB backup.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbBackupRequest extends GenericMongodbRequest {
    private String dbInstanceId;

    private String backupMethod;

    private String backupId;

    private String backupDescription;

    public String getDbInstanceId() {
        return dbInstanceId;
    }

    public void setDbInstanceId(String dbInstanceId) {
        this.dbInstanceId = dbInstanceId;
    }

    public String getBackupMethod() {
        return backupMethod;
    }

    public void setBackupMethod(String backupMethod) {
        this.backupMethod = backupMethod;
    }

    public String getBackupId() {
        return backupId;
    }

    public void setBackupId(String backupId) {
        this.backupId = backupId;
    }

    public String getBackupDescription() {
        return backupDescription;
    }

    public void setBackupDescription(String backupDescription) {
        this.backupDescription = backupDescription;
    }
}
