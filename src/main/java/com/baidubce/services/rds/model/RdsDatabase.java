package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsDatabase {
    private String dbName;
    private String characterSetName;
    private List<RdsAccountPrivilege> accountPrivileges;
    private String collate;
    private String ctype;
    private String owner;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public List<RdsAccountPrivilege> getAccountPrivileges() {
        return accountPrivileges;
    }

    public void setAccountPrivileges(List<RdsAccountPrivilege> accountPrivileges) {
        this.accountPrivileges = accountPrivileges;
    }

    public String getCollate() {
        return collate;
    }

    public void setCollate(String collate) {
        this.collate = collate;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
