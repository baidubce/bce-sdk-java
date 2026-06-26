package com.baidubce.services.scs.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Request to restart scs instance.
 */
public class ScsRestartInstanceRequest extends AbstractBceRequest {

    private String instanceId;
    private Boolean isDefer;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Boolean getIsDefer() {
        return isDefer;
    }

    public void setIsDefer(Boolean defer) {
        isDefer = defer;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
