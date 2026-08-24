/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Order configuration that can carry the certificate application in one shot.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AutoOrderConfig extends PriceConfig {
    /**
     * Whether the certificate is applied for immediately after the order is paid.
     */
    private boolean autoApply;
    /**
     * Detailed certificate application parameters, used when autoApply is true.
     */
    private ApplyCertRequest applyCertRequest;
}
