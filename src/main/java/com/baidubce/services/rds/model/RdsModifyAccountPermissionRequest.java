package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsModifyAccountPermissionRequest extends AbstractBceRequest {
    private String instanceId;
    private String accountName;
    private List<DatabasePrivilege> databasePrivileges;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public List<DatabasePrivilege> getDatabasePrivileges() {
        return databasePrivileges;
    }

    public void setDatabasePrivileges(List<DatabasePrivilege> databasePrivileges) {
        this.databasePrivileges = databasePrivileges;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
