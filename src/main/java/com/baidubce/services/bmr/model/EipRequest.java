package com.baidubce.services.bmr.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class EipRequest extends AbstractBceRequest {
    private String clusterId;
    private String instanceId;

    public EipRequest withClusterId(String clusterId) {
        this.setClusterId(clusterId);
        return this;
    }

    public EipRequest withInstanceId(String instanceId) {
        this.setInstanceId(instanceId);
        return this;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Override public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
