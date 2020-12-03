package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

public class GetDomainOCSPResponse extends CdnResponse {
    private boolean ocspSwitchx;

    /**
     * @return ocspSwitch
     */
    public boolean getOCSPSwitch() {
        return ocspSwitchx;
    }

    /**
     * @param ocspSwitch OCSP
     */
    public void setOCSPSwitch(boolean ocspSwitch) {
        this.ocspSwitchx = ocspSwitch;
    }

}
