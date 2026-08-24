/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.type;

import com.baidubce.services.cas.model.CasConstants;

import lombok.Getter;

/**
 * Type of the certificate order.
 */
@Getter
public enum OrderType {
    NEW(CasConstants.CAS_DAILY_PRICE_POLICY),
    RENEW(CasConstants.CAS_DAILY_PRICE_POLICY);

    private final String pricePolicyName;

    OrderType(String pricePolicyName) {
        this.pricePolicyName = pricePolicyName;
    }
}
