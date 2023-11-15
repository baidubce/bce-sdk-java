package com.baidubce.services.cdn.model.certificate;

public class HttpsDomain {
    private int status;
    private String domain;
    private int certType;
    private String certStopTime;
    private String certStartTime;
    private String certName;
    private String certId;
    private String certCommonName;

    public HttpsDomain() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getCertType() {
        return certType;
    }

    public void setCertType(int certType) {
        this.certType = certType;
    }

    public String getCertStopTime() {
        return certStopTime;
    }

    public void setCertStopTime(String certStopTime) {
        this.certStopTime = certStopTime;
    }

    public String getCertStartTime() {
        return certStartTime;
    }

    public void setCertStartTime(String certStartTime) {
        this.certStartTime = certStartTime;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getCertCommonName() {
        return certCommonName;
    }

    public void setCertCommonName(String certCommonName) {
        this.certCommonName = certCommonName;
    }
}
