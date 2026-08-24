/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.type;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;

/**
 * Brand (certificate authority) of the certificate.
 */
@Getter
public enum CertBrand {
    @Deprecated
    SYMANTEC("Symantec"),
    // the actual root is digicert
    SECURESITE("digicertsecuresite"),
    // the actual root is digicert
    GEOTRUST("GEOTRUST"),
    GLOBALSIGN("GlobalSign"),
    CFCA("CFCA"),
    TRUSTASIA("trustasia"),
    BAIDUTRUST("BaiduTrust"),
    BIG_RESOURCE("BIG_RESOURCE"),
    SECURESITE_ITRUS_CHINA("securesiteITrusChina"),
    GLOBALSIGN_ITRUS_CHINA("globalsignITrusChina"),
    DIGICERT("Digicert basic"),
    SSLTRUS("sslTrus"),
    UNITRUST("UniTrust"),
    TLC("TLC");

    private final String billingType;

    CertBrand(String billingType) {
        this.billingType = billingType;
    }

    @JsonCreator
    public static CertBrand parse(String name) {
        return CertBrand.valueOf(name);
    }

    /**
     * Days before expiration during which the certificate can be renewed.
     *
     * @return the renewable period in days
     */
    public int renewablePeriod() {
        return 30;
    }
}
