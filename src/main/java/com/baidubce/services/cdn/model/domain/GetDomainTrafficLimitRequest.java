package com.baidubce.services.cdn.model.domain;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class GetDomainTrafficLimitRequest extends AbstractBceRequest {

    private String domain;

    public GetDomainTrafficLimitRequest() {
    }

    public GetDomainTrafficLimitRequest(String domain) {
        this.domain = domain;
    }

    public GetDomainTrafficLimitRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
