/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model;

/**
 * Constants shared by the CAS (SSL certificate) models.
 */
public final class CasConstants {

    /**
     * Datetime pattern used by all CAS timestamp fields.
     */
    public static final String UTC_DATE = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    /**
     * Timezone used by all CAS timestamp fields.
     */
    public static final String UTC = "UTC";

    /**
     * Price policy of ordinary daily-price orders.
     */
    public static final String CAS_DAILY_PRICE_POLICY = "cas_price_daily";

    /**
     * Price policy of internal orders.
     */
    public static final String CAS_INTERNAL_POLICY = "cas_internal";

    /**
     * Price policy of big-resource-package orders.
     */
    public static final String CAS_BIG_PRICE_POLICY = "cas_big_daily";

    private CasConstants() {
    }
}
