package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * create by hansongda on 20/12/01
 */
public class GetDomainQUICSwitchResponse extends CdnResponse {
    private boolean QUIC;

    public boolean isQUIC() {
        return QUIC;
    }

    public void setQUIC(boolean QUIC) {
        this.QUIC = QUIC;
    }
}
