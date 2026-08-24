/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.request;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Request of querying the detail of a certificate.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CertDetailRequest extends AbstractBceRequest {
    /**
     * Certificate id.
     */
    private String certId;
    /**
     * Order id, optional. Used to locate a specific order of the certificate.
     */
    private String orderId;
    /**
     * Query type, "normal" by default.
     */
    private String type = "normal";

    @JsonAnySetter
    public void handleUnknown(String key, Object value) {
        if ("productId".equals(key) && this.certId == null && value != null) {
            this.certId = value.toString();
        }
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
