package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

public class GetDomainUaAclResponse  extends CdnResponse {
    private UaAcl uaAcl;

    public UaAcl getUaAcl() {
        return uaAcl;
    }

    public void setUaAcl(UaAcl uaAcl) {
        this.uaAcl = uaAcl;
    }
}
