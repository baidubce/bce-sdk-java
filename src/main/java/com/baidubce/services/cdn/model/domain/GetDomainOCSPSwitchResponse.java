package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * create by hansongda on 20/12/01
 */
public class GetDomainOCSPSwitchResponse extends CdnResponse {
    private boolean OCSP;

    public boolean isOCSP() {
        return OCSP;
    }

    public void setOCSP(boolean OCSP) {
        this.OCSP = OCSP;
    }
}
