package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SecurityIps extends AbstractBceRequest {
    private List<String> securityIps;

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
