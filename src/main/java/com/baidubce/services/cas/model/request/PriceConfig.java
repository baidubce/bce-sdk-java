/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.request;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.cas.model.type.CertBrand;
import com.baidubce.services.cas.model.type.CertType;
import com.baidubce.services.cas.model.type.OrderType;
import com.baidubce.services.cas.model.type.ProductType;
import com.baidubce.services.cas.model.type.TimeUnit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Configuration used to calculate the price of a certificate.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceConfig extends AbstractBceRequest {
    /**
     * Certificate brand.
     */
    @JsonProperty("brand")
    private CertBrand certBrand;
    /**
     * Certificate validation level.
     */
    private CertType certType;
    /**
     * Certificate product type.
     */
    private ProductType productType;
    /**
     * Number of standard domains.
     */
    @JsonProperty("domainNumber")
    private int standardDomainNum;
    /**
     * Number of wildcard domains.
     */
    @JsonProperty("wildcardNumber")
    private int wildcardDomainNum;
    /**
     * Purchase duration.
     */
    @JsonProperty("purchaseLength")
    private int duration;
    /**
     * Unit of the purchase duration.
     */
    private TimeUnit timeUnit = TimeUnit.YEAR;
    /**
     * Order type.
     */
    private OrderType orderType = OrderType.NEW;
    /**
     * Source page of the request.
     */
    private String pageResource = "";
    /**
     * Whether the order is placed by a virtual merchant.
     */
    private boolean virtualMerchant;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
