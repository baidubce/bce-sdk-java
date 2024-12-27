package com.baidubce.services.rds.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsResourceInfo {
    private String name;
    private String extra;
    private String parentUuid;
    private String groupId;
    private String accountId;
    private String userId;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Timestamp deleteTime;
    private Timestamp bindTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getParentUuid() {
        return parentUuid;
    }

    public void setParentUuid(String parentUuid) {
        this.parentUuid = parentUuid;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Timestamp deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Timestamp getBindTime() {
        return bindTime;
    }

    public void setBindTime(Timestamp bindTime) {
        this.bindTime = bindTime;
    }
}
