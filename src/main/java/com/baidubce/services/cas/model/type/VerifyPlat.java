/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.type;

import lombok.Getter;

/**
 * Platform used to verify the organization information.
 */
@Getter
public enum VerifyPlat {
    NONE(""),
    QCC("企查查"),
    BDMAP("百度地图");

    private final String siteName;

    VerifyPlat(String siteName) {
        this.siteName = siteName;
    }
}
