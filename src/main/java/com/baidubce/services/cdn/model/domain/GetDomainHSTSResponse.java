package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * create by hansongda on 20/12/01
 */
public class GetDomainHSTSResponse extends CdnResponse {
    private Hsts HSTS;

    public Hsts getHSTS() {
        return HSTS;
    }

    public void setHSTS(Hsts HSTS) {
        this.HSTS = HSTS;
    }
}
