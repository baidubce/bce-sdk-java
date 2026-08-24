/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.response;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Response of checking whether a free certificate can still be purchased.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckPurchasableResponse extends AbstractBceResponse {
    /**
     * Whether a free DV certificate can be purchased.
     */
    @JsonProperty("enablePurchaseDV")
    private boolean dvPurchaseEnabled;
    /**
     * Number of free certificates already owned.
     */
    private int freeCount;
}
