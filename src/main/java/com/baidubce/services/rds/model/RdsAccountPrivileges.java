package com.baidubce.services.rds.model;

/**
 * Database privileges
 */
public class RdsAccountPrivileges {

    private String accountName;
    private String authType;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }
}
