/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.response;

import com.baidubce.model.AbstractBceResponse;

/**
 * Response carrying a binary payload, such as a certificate package or a confirmation
 * letter template.
 */
public class CertFileResponse extends AbstractBceResponse {

    private byte[] content;

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
