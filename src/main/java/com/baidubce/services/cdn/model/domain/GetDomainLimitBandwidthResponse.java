package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

public class GetDomainLimitBandwidthResponse extends CdnResponse {
    private LimitBandwidth limitBandwidth;

    public LimitBandwidth getLimitBandwidth() {
        return limitBandwidth;
    }

    public void setLimitBandwidth(LimitBandwidth limitBandwidth) {
        this.limitBandwidth = limitBandwidth;
    }
}
