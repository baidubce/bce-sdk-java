/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cert.model;

import java.util.Date;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * Certificate creation request.
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CertCreateRequest extends AbstractBceRequest {

    private String certName;

    private String certServerData;

    private String certPrivateData;

    private String certLinkData;

    private String uploadPublicKey;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CertConstant.DATETIME_FORMAT, timezone = "UTC")
    private Date createTime;

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

    public String getUploadPublicKey() {
        return uploadPublicKey;
    }

    public void setUploadPublicKey(String uploadPublicKey) {
        this.uploadPublicKey = uploadPublicKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
