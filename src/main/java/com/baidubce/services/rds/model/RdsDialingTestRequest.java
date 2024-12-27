package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * the Request of Dialing test interface
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsDialingTestRequest extends AbstractBceRequest {

    private String instanceId;

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
    public String getInstanceId() {
        return instanceId;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
