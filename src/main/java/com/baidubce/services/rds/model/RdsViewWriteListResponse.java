package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsViewWriteListResponse extends AbstractBceResponse {
    private List<String> securityIps;

    public List<String> getSecurityIps() {
        return securityIps;
    }

    public void setSecurityIps(List<String> securityIps) {
        this.securityIps = securityIps;
    }
}
