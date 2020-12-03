package com.baidubce.services.cdn.model.certificate;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.Date;

public class GetDomainCertResponse extends CdnResponse {
    private String certId;
    private String certName;
    private String certCommonName;
    private String certDNSNames;
    private Date certStartTime;
    private Date certStopTime;
    private Date certCreateTime;
    private Date certUpdateTime;

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getCertCommonName() {
        return certCommonName;
    }

    public void setCertCommonName(String certCommonName) {
        this.certCommonName = certCommonName;
    }

    public String getCertDNSNames() {
        return certDNSNames;
    }

    public void setCertDNSNames(String certDNSNames) {
        this.certDNSNames = certDNSNames;
    }

    public Date getCertStartTime() {
        return certStartTime;
    }

    public void setCertStartTime(Date certStartTime) {
        this.certStartTime = certStartTime;
    }

    public Date getCertStopTime() {
        return certStopTime;
    }

    public void setCertStopTime(Date certStopTime) {
        this.certStopTime = certStopTime;
    }

    public Date getCertCreateTime() {
        return certCreateTime;
    }

    public void setCertCreateTime(Date certCreateTime) {
        this.certCreateTime = certCreateTime;
    }

    public Date getCertUpdateTime() {
        return certUpdateTime;
    }

    public void setCertUpdateTime(Date certUpdateTime) {
        this.certUpdateTime = certUpdateTime;
    }
}
