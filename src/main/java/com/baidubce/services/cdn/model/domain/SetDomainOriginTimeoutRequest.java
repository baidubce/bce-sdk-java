package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

public class SetDomainOriginTimeoutRequest extends CdnRequest {
    private String domain;
    private OriginTimeout originTimeout;

    public SetDomainOriginTimeoutRequest() {
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public SetDomainOriginTimeoutRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public OriginTimeout getOriginTimeout() {
        return originTimeout;
    }

    public void setOriginTimeout(OriginTimeout originTimeout) {
        this.originTimeout = originTimeout;
    }

    public SetDomainOriginTimeoutRequest withOriginTimeout(OriginTimeout originTimeout) {
        this.originTimeout = originTimeout;
        return this;
    }
}
