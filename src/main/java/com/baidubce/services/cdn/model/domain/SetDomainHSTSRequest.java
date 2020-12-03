package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * HTTP Strict Transport Security
 */
public class SetDomainHSTSRequest extends CdnRequest {

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
}
