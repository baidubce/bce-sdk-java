package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * create by hansongda on 20/12/01
 */
public class GetDomainOCSPSwitchResponse extends CdnResponse {
    private boolean ocsp;

    public boolean isOcsp() {
        return ocsp;
    }

    public void setOcsp(boolean ocsp) {
        this.ocsp = ocsp;
    }
}
