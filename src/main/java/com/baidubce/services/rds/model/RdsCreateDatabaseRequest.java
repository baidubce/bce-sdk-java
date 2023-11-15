package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Create the rds database
 */
public class RdsCreateDatabaseRequest extends AbstractBceRequest {

    private String instanceId;
    private List<RdsAccountPrivileges> accountPrivileges = new ArrayList<RdsAccountPrivileges>();
    private RdsCharacterSet characterSetName;
    private String dbName;
    private String remark;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public List<RdsAccountPrivileges> getAccountPrivileges() {
        return accountPrivileges;
    }

    public void setAccountPrivileges(List<RdsAccountPrivileges> accountPrivileges) {
        this.accountPrivileges = accountPrivileges;
    }

    public RdsCharacterSet getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(RdsCharacterSet characterSetName) {
        this.characterSetName = characterSetName;
    }

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

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
