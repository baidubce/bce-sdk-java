package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class BccAutoRenewRequest extends AbstractBceRequest {

    private String instanceId;

    private String renewTimeUnit;

    private Integer renewTime;

    private boolean renewEip = true;

    private boolean renewCds = true;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public BccAutoRenewRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    public String getRenewTimeUnit() {
        return renewTimeUnit;
    }

    public void setRenewTimeUnit(String renewTimeUnit) {
        this.renewTimeUnit = renewTimeUnit;
    }

    public BccAutoRenewRequest withRenewTimeUnit(String renewTimeUnit) {
        this.renewTimeUnit = renewTimeUnit;
        return this;
    }

    public Integer getRenewTime() {
        return renewTime;
    }

    public void setRenewTime(Integer renewTime) {
        this.renewTime = renewTime;
    }

    public BccAutoRenewRequest withRenewTime(Integer renewTime) {
        this.renewTime = renewTime;
        return this;
    }

    public boolean isRenewEip() {
        return renewEip;
    }

    public void setRenewEip(boolean renewEip) {
        this.renewEip = renewEip;
    }

    public BccAutoRenewRequest withRenewEip(boolean renewEip) {
        this.renewEip = renewEip;
        return this;
    }

    public boolean isRenewCds() {
        return renewCds;
    }

    public void setRenewCds(boolean renewCds) {
        this.renewCds = renewCds;
    }

    public BccAutoRenewRequest withRenewCds(boolean renewCds) {
        this.renewCds = renewCds;
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
