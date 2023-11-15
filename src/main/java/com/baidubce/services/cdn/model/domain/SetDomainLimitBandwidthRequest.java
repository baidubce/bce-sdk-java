package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

public class SetDomainLimitBandwidthRequest extends CdnRequest {
    private String domain;
    private LimitBandwidth limitBandwidth;

    public SetDomainLimitBandwidthRequest() {
    }

    public SetDomainLimitBandwidthRequest(String domain, LimitBandwidth limitBandwidth) {
        this.domain = domain;
        this.limitBandwidth = limitBandwidth;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @param domain the domain name
     * @return returns this object
     */
    public SetDomainLimitBandwidthRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public LimitBandwidth getLimitBandwidth() {
        return limitBandwidth;
    }

    public void setLimitBandwidth(LimitBandwidth limitBandwidth) {
        this.limitBandwidth = limitBandwidth;
    }

    public SetDomainLimitBandwidthRequest withLimitBandwidth(LimitBandwidth limitBandwidth) {
        this.limitBandwidth = limitBandwidth;
        return this;
    }
}
