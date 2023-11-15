package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * The account info of rds
 */
public class RdsAccount extends AbstractBceResponse {

    private String accountName;
    private String remark;
    private RdsAccountStatus status;
    private String type;
    private RdsAccountType accountType;
    private List<RdsDatabasePrivileges> databasePrivileges;
    private String desc;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public RdsAccountStatus getStatus() {
        return status;
    }

    public void setStatus(RdsAccountStatus status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RdsAccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(RdsAccountType accountType) {
        this.accountType = accountType;
    }

    public List<RdsDatabasePrivileges> getDatabasePrivileges() {
        return databasePrivileges;
    }

    public void setDatabasePrivileges(List<RdsDatabasePrivileges> databasePrivileges) {
        this.databasePrivileges = databasePrivileges;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
