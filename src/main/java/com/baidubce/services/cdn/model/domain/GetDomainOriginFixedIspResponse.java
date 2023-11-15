package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

public class GetDomainOriginFixedIspResponse extends CdnResponse {
    private Boolean originFixedISP;

    public GetDomainOriginFixedIspResponse() {
    }

    public Boolean getOriginFixedISP() {
        return originFixedISP;
    }

    public void setOriginFixedISP(Boolean originFixedISP) {
        this.originFixedISP = originFixedISP;
    }
}
