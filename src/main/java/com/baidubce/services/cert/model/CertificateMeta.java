/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cert.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CertificateMeta json model.
 *
 * @author dingxiaomin (dingxiaomin@baidu.com)
 */
public class CertificateMeta {

    private String certId;

    private String certName;

    private String certCommonName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CertConstant.DATETIME_FORMAT, timezone = "UTC")
    private Date certStartTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CertConstant.DATETIME_FORMAT, timezone = "UTC")
    private Date certStopTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CertConstant.DATETIME_FORMAT, timezone = "UTC")
    private Date certCreateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CertConstant.DATETIME_FORMAT, timezone = "UTC")
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
