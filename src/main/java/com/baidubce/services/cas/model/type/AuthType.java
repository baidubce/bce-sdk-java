/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.type;

/**
 * Domain control validation (DCV) method.
 */
public enum AuthType {
    DNS,
    DNS_TXT,
    FILE,
    TXT,
    EMAIL("EMAIL"),
    HTTP("HTTP_CSR_HASH"),
    HTTPS("HTTPS_CSR_HASH"),
    CNAME("CNAME_CSR_HASH"),
    IP("IP_ADDRESS_PRE");

    private final String comodoValue;

    AuthType(String comodoValue) {
        this.comodoValue = comodoValue;
    }

    AuthType() {
        this.comodoValue = null;
    }

    public String getComodoValue() {
        return this.comodoValue;
    }

    public boolean isCNAME() {
        return this == CNAME;
    }

    public boolean isTXT() {
        return this == TXT || this == DNS;
    }

    public boolean isDNS() {
        return isCNAME() || isTXT();
    }

    public boolean isFile() {
        return this == FILE || this == HTTP || this == HTTPS;
    }
}
