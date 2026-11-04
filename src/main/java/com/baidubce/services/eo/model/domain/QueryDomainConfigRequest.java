package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.EoRequest;

/**
 * The request for querying an accelerate domain configuration.
 */
public class QueryDomainConfigRequest extends EoRequest {
    /**
     * The accelerate domain.
     */
    private String domain;

    /**
     * @param domain
     * @return this object
     */
    public QueryDomainConfigRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    /**
     * @return domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }
}
