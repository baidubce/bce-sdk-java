package com.baidubce.services.gaiadb.model;

import java.util.List;
import java.util.Map;

public class SnapshotCreateRequest extends GenericGaiadbRequest {
    private String clusterId;
    private String backupType;
    private List<Map<String, Object>> databases;

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getBackupType() {
        return backupType;
    }

    public void setBackupType(String backupType) {
        this.backupType = backupType;
    }

    public List<Map<String, Object>> getDatabases() {
        return databases;
    }

    public void setDatabases(List<Map<String, Object>> databases) {
        this.databases = databases;
    }
}
