package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

public class SetDomainOriginFixedIspRequest extends CdnRequest {
    private String domain;
    private Boolean originFixedISP;

    public SetDomainOriginFixedIspRequest() {
    }

    public SetDomainOriginFixedIspRequest(Boolean originFixedISP) {
        this.originFixedISP = originFixedISP;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public SetDomainOriginFixedIspRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public Boolean getOriginFixedISP() {
        return originFixedISP;
    }

    public void setOriginFixedISP(Boolean originFixedISP) {
        this.originFixedISP = originFixedISP;
    }

    public SetDomainOriginFixedIspRequest withOriginFixedISP(Boolean originFixedISP) {
        this.setOriginFixedISP(originFixedISP);
        return this;
    }
}
