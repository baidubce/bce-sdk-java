package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

public class GetDomainOriginProtocolResponse extends CdnResponse {
    private OriginProtocol originProtocol;

    public OriginProtocol getOriginProtocol() {
        return originProtocol;
    }

    public void setOriginProtocol(OriginProtocol originProtocol) {
        this.originProtocol = originProtocol;
    }
}
