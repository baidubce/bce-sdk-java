/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.type;

import lombok.Getter;

/**
 * Identity document type of a contact person. Required by CFCA certificates.
 */
@Getter
public enum IdentityType {
    ID_CARD("身份证"),
    PASSPORT("护照");

    private final String desc;

    IdentityType(String desc) {
        this.desc = desc;
    }
}
