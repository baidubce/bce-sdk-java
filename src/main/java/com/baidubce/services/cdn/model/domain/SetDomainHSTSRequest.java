package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * HTTP Strict Transport Security
 */
public class SetDomainHSTSRequest extends CdnRequest {
    private String domain;
    private Hsts HSTS;

    public Hsts getHSTS() {
        return HSTS;
    }

    public void setHSTS(Hsts HSTS) {
        this.HSTS = HSTS;
    }

    public SetDomainHSTSRequest withHSTS(Hsts HSTS) {
        setHSTS(HSTS);
        return this;
    }

    /**
     * @return domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain the domain name
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @param domain the domain name
     * @return returns this object
     */
    public SetDomainHSTSRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }
}
