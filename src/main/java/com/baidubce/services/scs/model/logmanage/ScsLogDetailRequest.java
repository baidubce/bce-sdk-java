package com.baidubce.services.scs.model.logmanage;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ScsLogDetailRequest extends AbstractBceRequest {
    private String instanceId;
    private String logId;
    private Integer validSeconds;

    @JsonIgnore
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public Integer getValidSeconds() {
        return validSeconds;
    }

    public void setValidSeconds(Integer validSeconds) {
        this.validSeconds = validSeconds;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
