package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * create by songda on 20/12/01
 */
public class SetDomainRetryOriginRequest extends CdnRequest {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String domain;
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private RetryOrigin retryOrigin;

    public RetryOrigin getRetryOrigin() {
        return retryOrigin;
    }

    public void setRetryOrigin(RetryOrigin retryOrigin) {
        this.retryOrigin = retryOrigin;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public SetDomainRetryOriginRequest withRetryOrigin(RetryOrigin retryOrigin) {
        setRetryOrigin(retryOrigin);
        return this;
    }

    public SetDomainRetryOriginRequest withDomain(String domain) {
        setDomain(domain);
        return this;
    }

}
