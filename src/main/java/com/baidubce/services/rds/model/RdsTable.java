package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsTable {
    private String tableName;
    private String newTablename;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getNewTablename() {
        return newTablename;
    }

    public void setNewTablename(String newTablename) {
        this.newTablename = newTablename;
    }
}
