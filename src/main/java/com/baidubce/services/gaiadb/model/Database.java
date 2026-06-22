package com.baidubce.services.gaiadb.model;

import java.util.List;

public class Database extends GenericGaiadbRequest {
    private String dbName;
    private String remark;
    private String dbStatus;
    private String characterSetName;
    private List<AccountPrivilege> accountPrivileges;
    private String etag;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDbStatus() {
        return dbStatus;
    }

    public void setDbStatus(String dbStatus) {
        this.dbStatus = dbStatus;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public List<AccountPrivilege> getAccountPrivileges() {
        return accountPrivileges;
    }

    public void setAccountPrivileges(List<AccountPrivilege> accountPrivileges) {
        this.accountPrivileges = accountPrivileges;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public static class AccountPrivilege {
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
}
