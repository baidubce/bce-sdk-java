package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Table;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsRecoveryToSourceInstanceModel {
    private String restoreMode;
    private String dbName;
    private String newDbname;
    private List<RdsTable> tables;

    public String getRestoreMode() {
        return restoreMode;
    }

    public void setRestoreMode(String restoreMode) {
        this.restoreMode = restoreMode;
    }

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

    public List<RdsTable> getTables() {
        return tables;
    }

    public void setTables(List<RdsTable> tables) {
        this.tables = tables;
    }
}
