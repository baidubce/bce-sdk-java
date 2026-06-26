package com.baidubce.services.scs.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Request to set scs auto scaling config.
 */
public class ScsAutoScalingConfigRequest extends AbstractBceRequest {

    private String instanceId;
    private ScsMemAutoScalingConfig memSpec;

    @JsonIgnore
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public ScsMemAutoScalingConfig getMemSpec() {
        return memSpec;
    }

    public void setMemSpec(ScsMemAutoScalingConfig memSpec) {
        this.memSpec = memSpec;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
