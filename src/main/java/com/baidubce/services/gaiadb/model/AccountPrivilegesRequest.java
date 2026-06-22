package com.baidubce.services.gaiadb.model;

import java.util.List;

public class AccountPrivilegesRequest extends GenericGaiadbRequest {
    private String etag;
    private List<Account.DatabasePrivilege> databasePrivileges;

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public List<Account.DatabasePrivilege> getDatabasePrivileges() {
        return databasePrivileges;
    }

    public void setDatabasePrivileges(List<Account.DatabasePrivilege> databasePrivileges) {
        this.databasePrivileges = databasePrivileges;
    }
}
