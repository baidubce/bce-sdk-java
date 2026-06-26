package com.baidubce.services.scs.model.securitygroup;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Response of scs security IP list.
 */
public class ScsSecurityIpResponse extends AbstractBceResponse {

    private List<String> securityIps;

    public List<String> getSecurityIps() {
        return securityIps;
    }

    public void setSecurityIps(List<String> securityIps) {
        this.securityIps = securityIps;
    }
}
