package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * create by songda on 20/12/01
 */
public class SetDomainRetryOriginRequest extends CdnRequest {
    private RetryOrigin retryOrigin;

    public RetryOrigin getRetryOrigin() {
        return retryOrigin;
    }

    public void setRetryOrigin(RetryOrigin retryOrigin) {
        this.retryOrigin = retryOrigin;
    }

    public SetDomainRetryOriginRequest withRetryOrigin(RetryOrigin retryOrigin) {
        setRetryOrigin(retryOrigin);
        return this;
    }

}
