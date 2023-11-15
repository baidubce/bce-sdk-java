package com.baidubce.services.rds.model;

import com.baidubce.BceConstants;
import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The request to get rds slog log
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsSlowLogRequest extends AbstractBceRequest {

    private String instanceId;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = BceConstants.DEFAULT_DATETIME_FORMAT,
            timezone = "UTC"
    )
    private Date startTime;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = BceConstants.DEFAULT_DATETIME_FORMAT,
            timezone = "UTC"
    )
    private Date endTime;
    private int pageNo = 1;
    private int pageSize = 1000;
    private List<String> dbName;
    private List<String> userName;
    private List<String> hostIp;
    private String sql;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<String> getDbName() {
        return dbName;
    }

    public void setDbName(List<String> dbName) {
        this.dbName = dbName;
    }

    public List<String> getUserName() {
        return userName;
    }

    public void setUserName(List<String> userName) {
        this.userName = userName;
    }

    public List<String> getHostIp() {
        return hostIp;
    }

    public void setHostIp(List<String> hostIp) {
        this.hostIp = hostIp;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void addDbName(String dbName) {
        if (this.dbName == null) {
            this.dbName = new ArrayList<String>();
        }
        this.dbName.add(dbName);
    }

    public void addUserName(String userName) {
        if (this.userName == null) {
            this.userName = new ArrayList<String>();
        }
        this.userName.add(userName);
    }

    public void addHostIp(String hostIp) {
        if (this.hostIp == null) {
            this.hostIp = new ArrayList<String>();
        }
        this.hostIp.add(hostIp);
    }
}
