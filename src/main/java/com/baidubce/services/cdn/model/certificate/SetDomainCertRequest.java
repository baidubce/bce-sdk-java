package com.baidubce.services.cdn.model.certificate;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * create by hansongda on 20/12/02
 */
public class SetDomainCertRequest extends CdnRequest {
    private String domain;

    /**
     * 只能取值"ON"或者"OFF"，表示是否开启https支持，“ON”开启，“OFF”关闭
     */
    private String httpsEnable;

    /**
     * 证书内容
     */
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public SetDomainCertRequest withHttpsEnable(String httpsEnable) {
        setHttpsEnable(httpsEnable);
        return this;
    }

    public SetDomainCertRequest withCertificate(Cert certificate) {
        setCertificate(certificate);
        return this;
    }

    /**
     * @param domain the domain name
     * @return returns this object
     */
    public SetDomainCertRequest withDomain(String domain) {
        setDomain(domain);
        return this;
    }
}
