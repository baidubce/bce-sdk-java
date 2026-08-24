/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.type;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;

/**
 * Validation level of the certificate.
 */
public enum CertType {
    DV("DV(域名型)"),
    OV("OV(企业型)"),
    EV("EV(增强型)"),
    OVPRO("企业型专业版（OV PRO）证书"),
    EVPRO("增强型专业版（EV PRO)证书");

    @Getter
    private String name;

    CertType(String name) {
        this.name = name;
    }

    public boolean isOVOrEV() {
        return this == OV || this == EV || this == OVPRO || this == EVPRO;
    }

    public boolean isOV() {
        return this == OV || this == OVPRO;
    }

    @JsonCreator
    public static CertType parse(String name) {
        return CertType.valueOf(name);
    }
}
