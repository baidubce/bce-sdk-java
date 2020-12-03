package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * create by hansongda on 20/12/01
 */
public class GetDomainRetryOriginResponse extends CdnResponse {
    private RetryOrigin retryOrigin;

    public RetryOrigin getRetryOrigin() {
        return retryOrigin;
    }

    public void setRetryOrigin(RetryOrigin retryOrigin) {
        this.retryOrigin = retryOrigin;
    }
}
