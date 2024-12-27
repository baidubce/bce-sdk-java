package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatabasePrivilegeList extends AbstractBceRequest {
    private List<DatabasePrivilege> databasePrivileges;

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
