package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Request of  Instance Update Time Window
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsUpdateTimeWindowRequest extends AbstractBceRequest {
    private String instanceId;
    private String maintainStartTime;
    private Integer maintainDuration; 
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getMaintainStartTime() {
        return maintainStartTime;
    }

    public void setMaintainStartTime(String maintainStartTime) {
        this.maintainStartTime = maintainStartTime;
    }

    public Integer getMaintainDuration() {
        return maintainDuration;
    }

    public void setMaintainDuration(Integer maintainDuration) {
        this.maintainDuration = maintainDuration;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
