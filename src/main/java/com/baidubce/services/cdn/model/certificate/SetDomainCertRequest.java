package com.baidubce.services.cdn.model.certificate;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * create by hansongda on 20/12/02
 */
public class SetDomainCertRequest extends CdnRequest {

    private String httpsEnable;

    private Cert certificate;

    public String getHttpsEnable() {
        return httpsEnable;
    }

    public void setHttpsEnable(String httpsEnable) {
        this.httpsEnable = httpsEnable;
    }

    public Cert getCertificate() {
        return certificate;
    }

    public void setCertificate(Cert certificate) {
        this.certificate = certificate;
    }

    public SetDomainCertRequest withHttpsEnable(String httpsEnable) {
        setHttpsEnable(httpsEnable);
        return this;
    }

    public SetDomainCertRequest withCertificate(Cert certificate) {
        setCertificate(certificate);
        return this;
    }
}
