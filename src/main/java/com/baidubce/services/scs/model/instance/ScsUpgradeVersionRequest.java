package com.baidubce.services.scs.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Request to upgrade scs engine version.
 */
public class ScsUpgradeVersionRequest extends AbstractBceRequest {

    private String instanceId;
    private String kernelVersion;
    private Boolean isDefer;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getKernelVersion() {
        return kernelVersion;
    }

    public void setKernelVersion(String kernelVersion) {
        this.kernelVersion = kernelVersion;
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
