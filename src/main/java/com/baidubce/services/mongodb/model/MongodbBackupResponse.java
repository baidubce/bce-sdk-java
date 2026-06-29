package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Response of creating a MongoDB backup.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbBackupResponse extends GenericMongodbResponse {
    private String backupId;

    public String getBackupId() {
        return backupId;
    }

    public void setBackupId(String backupId) {
        this.backupId = backupId;
    }
}
