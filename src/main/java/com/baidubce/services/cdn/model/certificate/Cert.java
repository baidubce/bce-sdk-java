package com.baidubce.services.cdn.model.certificate;

import com.baidubce.services.cdn.model.JsonObject;

public class Cert extends JsonObject {
    private String certName;
    private String certServerData;
    private String certPrivateData;
    private String certLinkData;
    private Integer certType;

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getCertServerData() {
        return certServerData;
    }

    public void setCertServerData(String certServerData) {
        this.certServerData = certServerData;
    }

    public String getCertPrivateData() {
        return certPrivateData;
    }

    public void setCertPrivateData(String certPrivateData) {
        this.certPrivateData = certPrivateData;
    }

    public String getCertLinkData() {
        return certLinkData;
    }

    public void setCertLinkData(String certLinkData) {
        this.certLinkData = certLinkData;
    }

    public Integer getCertType() {
        return certType;
    }

    public void setCertType(Integer certType) {
        this.certType = certType;
    }

    public Cert withCertName(String certName) {
        setCertName(certName);
        return this;
    }

    public Cert withCertServerData(String certServerData) {
        setCertServerData(certServerData);
        return this;
    }

    public Cert withCertPrivateData(String certPrivateData) {
        setCertPrivateData(certPrivateData);
        return this;
    }

    public Cert withCertLinkData(String certLinkData) {
        setCertLinkData(certLinkData);
        return this;
    }

    public Cert withCertType(Integer certType) {
        setCertType(certType);
        return this;
    }
}
