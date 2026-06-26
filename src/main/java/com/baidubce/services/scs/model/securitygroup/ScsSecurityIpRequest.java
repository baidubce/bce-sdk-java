package com.baidubce.services.scs.model.securitygroup;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

/**
 * Request to add or delete scs security IPs.
 */
public class ScsSecurityIpRequest extends AbstractBceRequest {

    private String instanceId;
    private List<String> securityIps;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public List<String> getSecurityIps() {
        return securityIps;
    }

    public void setSecurityIps(List<String> securityIps) {
        this.securityIps = securityIps;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
