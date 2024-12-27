package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Request of Restart the instance
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsRestartRequest extends AbstractBceRequest {
    private String instanceId;
    private String effectiveTime="immediate";
    public String getInstanceId() {
        return instanceId;
    }

    public String geteffectiveTime() {
        return effectiveTime;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public void seteffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
