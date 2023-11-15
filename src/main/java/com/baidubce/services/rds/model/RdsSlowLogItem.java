package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * The item of rds slow log list
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsSlowLogItem {

    private String instanceId;
    private String userName;
    private String dbName;
    private String hostIp;
    private Double queryTime;
    private Double lockTime;
    private Integer rowsExamined;
    @JsonProperty("rows_sent")
    private Integer rowsSent;
    private String sql;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date executeTime;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public Double getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Double queryTime) {
        this.queryTime = queryTime;
    }

    public Double getLockTime() {
        return lockTime;
    }

    public void setLockTime(Double lockTime) {
        this.lockTime = lockTime;
    }

    public Integer getRowsExamined() {
        return rowsExamined;
    }

    public void setRowsExamined(Integer rowsExamined) {
        this.rowsExamined = rowsExamined;
    }

    public Integer getRowsSent() {
        return rowsSent;
    }

    public void setRowsSent(Integer rowsSent) {
        this.rowsSent = rowsSent;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }
}
