package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * Quick UDP Internet Connection(QUIC)
 */
public class SetDomainQUICRequest extends CdnRequest {

    private boolean QUIC;

    public boolean isQUIC() {
        return QUIC;
    }

    public void setQUIC(boolean QUIC) {
        this.QUIC = QUIC;
    }

    /**
     * @param QUIC
     * @return this object
     */
    public SetDomainQUICRequest withQUIC(boolean QUIC) {
        setQUIC(QUIC);
        return this;
    }
}
