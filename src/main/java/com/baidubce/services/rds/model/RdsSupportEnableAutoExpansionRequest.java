package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Request of Does the instance support enabling automatic scaling
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsSupportEnableAutoExpansionRequest extends AbstractBceRequest {

    private String instanceId;

    public String getInstanceId() {
        return instanceId;
    }
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
