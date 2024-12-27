package com.baidubce.services.blb.model;

/**
 * Https listener additional certificate and domain
 */
public class AdditionalCertDomain {

    /**
     * The short id of certificate
     */
    private String certId;

    /**
     * The domain name of the certificate corresponding to the above certId
     */
    private String host;

    public AdditionalCertDomain() {
    }

    public AdditionalCertDomain(String certId, String host) {
        this.certId = certId;
        this.host = host;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "AdditionalCertDomain{" +
                "certId='" + certId + '\'' +
                ", host='" + host + '\'' +
                '}';
    }
}
