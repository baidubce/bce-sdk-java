/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cert.model;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Json response model of listing certs.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CertListResponse extends AbstractBceResponse {

    private List<CertificateMeta> certs;

    public List<CertificateMeta> getCerts() {
        return certs;
    }

    public void setCerts(List<CertificateMeta> certs) {
        this.certs = certs;
    }
}
