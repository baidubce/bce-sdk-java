/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.request;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Request of downloading the certificate file.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DownloadCertFileRequest extends AbstractBceRequest {
    /**
     * Query type.
     */
    private String type;
    /**
     * Password used to protect the downloaded file.
     */
    private String filePassword;
    /**
     * Order id, optional.
     */
    private String orderId = "";
    /**
     * Package format of the downloaded file, see CertFileType.
     */
    private String format;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
