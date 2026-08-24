/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.request;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Request of listing the certificates of the current account.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListCertsRequest extends AbstractBceRequest {
    /**
     * Page number, starting from 1.
     */
    private Integer pageNo;    /**
     * Number of certificates in one page.
     */
    private Integer pageSize;
    /**
     * Certificate brand, see CertBrand.
     */
    private String brand;
    /**
     * Certificate validation level, see CertType.
     */
    private String certType;
    /**
     * Certificate status.
     */
    private String status;
     /**
     * Sort order of the result.
     */
    private String order;
   @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
