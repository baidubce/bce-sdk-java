package com.baidubce.services.rds.model;

import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecoveryToSourceInstanceModel {
    private String dbName;
    private String newDbname;
    private String restoreMode;
    private List<RecoveryToSourceInstanceTableModel> tables;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getNewDbname() {
        return newDbname;
    }

    public void setNewDbname(String newDbname) {
        this.newDbname = newDbname;
    }

    public String getRestoreMode() {
        return restoreMode;
    }

    public void setRestoreMode(String restoreMode) {
        this.restoreMode = restoreMode;
    }

    public List<RecoveryToSourceInstanceTableModel> getTables() {
        return tables;
    }

    public void setTables(List<RecoveryToSourceInstanceTableModel> tables) {
        this.tables = tables;
    }
}
