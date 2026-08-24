/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain control validation information of a single domain.
 */
@Data
@NoArgsConstructor
public class DcvInfo {
    /**
     * Domain being validated.
     */
    private String domain;
    /**
     * Url of the validation file.
     */
    private String fileUrl;

    public DcvInfo(String domain, String fileUrl) {
        this.domain = domain;
        this.fileUrl = fileUrl;
    }
}
