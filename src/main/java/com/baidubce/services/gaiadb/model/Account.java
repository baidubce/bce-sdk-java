package com.baidubce.services.gaiadb.model;

import java.util.List;

public class Account extends GenericGaiadbRequest {
    private String accountName;
    private String remark;
    private String accountStatus;
    private String accountType;
    private List<DatabasePrivilege> databasePrivileges;
    private String password;
    private String etag;
    private List<String> authip;
    private List<String> authbns;
    private String authType;

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

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<DatabasePrivilege> getDatabasePrivileges() {
        return databasePrivileges;
    }

    public void setDatabasePrivileges(List<DatabasePrivilege> databasePrivileges) {
        this.databasePrivileges = databasePrivileges;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public List<String> getAuthip() {
        return authip;
    }

    public void setAuthip(List<String> authip) {
        this.authip = authip;
    }

    public List<String> getAuthbns() {
        return authbns;
    }

    public void setAuthbns(List<String> authbns) {
        this.authbns = authbns;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public static class DatabasePrivilege {
        private String dbName;
        private String authType;
        private List<String> privileges;

        public String getDbName() {
            return dbName;
        }

        public void setDbName(String dbName) {
            this.dbName = dbName;
        }

        public String getAuthType() {
            return authType;
        }

        public void setAuthType(String authType) {
            this.authType = authType;
        }

        public List<String> getPrivileges() {
            return privileges;
        }

        public void setPrivileges(List<String> privileges) {
            this.privileges = privileges;
        }
    }

    public static class AuthValue {
        private List<String> authip;
        private List<String> authbns;

        public List<String> getAuthip() {
            return authip;
        }

        public void setAuthip(List<String> authip) {
            this.authip = authip;
        }

        public List<String> getAuthbns() {
            return authbns;
        }

        public void setAuthbns(List<String> authbns) {
            this.authbns = authbns;
        }
    }
}
