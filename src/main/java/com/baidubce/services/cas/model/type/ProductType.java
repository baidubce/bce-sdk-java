/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.type;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;

/**
 * Domain coverage of the certificate product.
 */
@Getter
public enum ProductType {
    // single domain
    SINGLE("SSL_BASE"),
    // multi domain - standard domain
    DV_SSL_BASE("DV_SSL_BASE"),
    // multi domain
    MULTI("SSL_MULTI"),
    // wildcard domain
    WILDCARD("COMMON"),
    // multi domain - wildcard
    DV_COMMON("DV_COMMON"),
    // single domain pro
    SINGLE_PRO("PRO_BASE"),
    // multi domain pro
    MULTI_PRO("PRO_MULTI"),
    // wildcard domain pro
    WILDCARD_PRO("PRO_COMMON");

    private static final Set<ProductType> SINGLE_SET =
            Collections.unmodifiableSet(EnumSet.of(SINGLE, SINGLE_PRO));

    private static final Set<ProductType> MULTI_SET =
            Collections.unmodifiableSet(EnumSet.of(MULTI, MULTI_PRO));

    private static final Set<ProductType> WILDCARD_SET =
            Collections.unmodifiableSet(EnumSet.of(WILDCARD, WILDCARD_PRO));

    private final String billingName;

    ProductType(String billingName) {
        this.billingName = billingName;
    }

    @JsonCreator
    public static ProductType parse(String name) {
        return ProductType.valueOf(name);
    }

    public boolean isSingle() {
        return SINGLE_SET.contains(this);
    }

    public boolean isMulti() {
        return MULTI_SET.contains(this);
    }

    public boolean isWildcard() {
        return WILDCARD_SET.contains(this);
    }
}
