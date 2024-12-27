package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Auto renew the rds instance
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsAutoRenewRequest extends AbstractBceRequest {

    private List<String> instanceIds;
    private RdsRenewTimeUnit autoRenewTimeUnit;
    private int autoRenewTime = 1;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }

    public List<String> getInstanceIds() {
        return instanceIds;
    }

    public void setInstanceIds(List<String> instanceIds) {
        this.instanceIds = instanceIds;
    }

    public RdsRenewTimeUnit getAutoRenewTimeUnit() {
        return autoRenewTimeUnit;
    }

    public void setAutoRenewTimeUnit(RdsRenewTimeUnit autoRenewTimeUnit) {
        this.autoRenewTimeUnit = autoRenewTimeUnit;
    }

    public int getAutoRenewTime() {
        return autoRenewTime;
    }

    public void setAutoRenewTime(int autoRenewTime) {
        this.autoRenewTime = autoRenewTime;
    }

    public void addInstanceId(String instanceId) {
        if (instanceIds == null) {
            instanceIds = new ArrayList<String>();
        }
        instanceIds.add(instanceId);
    }
}
