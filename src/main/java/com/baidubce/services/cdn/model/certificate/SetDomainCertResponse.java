package com.baidubce.services.cdn.model.certificate;

import com.baidubce.services.cdn.model.CdnResponse;

public class SetDomainCertResponse extends CdnResponse {
    private String certId;

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }
}
