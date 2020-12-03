package com.baidubce.services.cdn.model.domain;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class GetDomainUaAclRequest extends AbstractBceRequest {

    private String domain;

    public GetDomainUaAclRequest() {
    }

    public GetDomainUaAclRequest(String domain) {
        this.domain = domain;
    }

    public GetDomainUaAclRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
