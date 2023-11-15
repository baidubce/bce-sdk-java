package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

public class GetDomainOriginTimeoutResponse extends CdnResponse {
    private OriginTimeout originTimeout;

    public GetDomainOriginTimeoutResponse() {
    }

    public OriginTimeout getOriginTimeout() {
        return originTimeout;
    }

    public void setOriginTimeout(OriginTimeout originTimeout) {
        this.originTimeout = originTimeout;
    }
}
