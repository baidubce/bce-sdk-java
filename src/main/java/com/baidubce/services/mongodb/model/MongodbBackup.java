package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * MongoDB backup detail.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbBackup extends GenericMongodbResponse {
    private String backupId;

    private long backupSize;

    private String backupMethod;

    private String backupMode;

    private String backupType;

    private String backupStatus;

    private String backupStartTime;

    private String backupEndTime;

    private String downloadUrl;

    private String downloadExpires;

    private String backupDescription;

    private List<ShardBackup> shardBackups;

    public String getBackupId() {
        return backupId;
    }

    public void setBackupId(String backupId) {
        this.backupId = backupId;
    }

    public long getBackupSize() {
        return backupSize;
    }

    public void setBackupSize(long backupSize) {
        this.backupSize = backupSize;
    }

    public String getBackupMethod() {
        return backupMethod;
    }

    public void setBackupMethod(String backupMethod) {
        this.backupMethod = backupMethod;
    }

    public String getBackupMode() {
        return backupMode;
    }

    public void setBackupMode(String backupMode) {
        this.backupMode = backupMode;
    }

    public String getBackupType() {
        return backupType;
    }

    public void setBackupType(String backupType) {
        this.backupType = backupType;
    }

    public String getBackupStatus() {
        return backupStatus;
    }

    public void setBackupStatus(String backupStatus) {
        this.backupStatus = backupStatus;
    }

    public String getBackupStartTime() {
        return backupStartTime;
    }

    public void setBackupStartTime(String backupStartTime) {
        this.backupStartTime = backupStartTime;
    }

    public String getBackupEndTime() {
        return backupEndTime;
    }

    public void setBackupEndTime(String backupEndTime) {
        this.backupEndTime = backupEndTime;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDownloadExpires() {
        return downloadExpires;
    }

    public void setDownloadExpires(String downloadExpires) {
        this.downloadExpires = downloadExpires;
    }

    public String getBackupDescription() {
        return backupDescription;
    }

    public void setBackupDescription(String backupDescription) {
        this.backupDescription = backupDescription;
    }

    public List<ShardBackup> getShardBackups() {
        return shardBackups;
    }

    public void setShardBackups(List<ShardBackup> shardBackups) {
        this.shardBackups = shardBackups;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ShardBackup {
        private String nodeId;

        private long backupSize;

        private String backupStartTime;

        private String backupEndTime;

        public String getNodeId() {
            return nodeId;
        }

        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        public long getBackupSize() {
            return backupSize;
        }

        public void setBackupSize(long backupSize) {
            this.backupSize = backupSize;
        }

        public String getBackupStartTime() {
            return backupStartTime;
        }

        public void setBackupStartTime(String backupStartTime) {
            this.backupStartTime = backupStartTime;
        }

        public String getBackupEndTime() {
            return backupEndTime;
        }

        public void setBackupEndTime(String backupEndTime) {
            this.backupEndTime = backupEndTime;
        }
    }
}
