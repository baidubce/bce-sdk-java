package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * The request of create account
 */
public class RdsCreateAccountRequest extends AbstractBceRequest {

    private String instanceId;
    private String clientToken;
    private String accountName;
    private String password;
    private RdsAccountType accountType;

    private List<RdsDatabasePrivileges> databasePrivileges;
    private String desc;
    private String type;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
