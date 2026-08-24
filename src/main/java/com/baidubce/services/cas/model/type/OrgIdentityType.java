/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.type;

import lombok.Getter;

/**
 * Identity document type of the organization. Required by CFCA certificates.
 */
@Getter
public enum OrgIdentityType {
    SOCIAL_CREDIT_CODE("社会信用代码"),
    OTHERS("其他");

    private final String desc;

    OrgIdentityType(String desc) {
        this.desc = desc;
    }
}
