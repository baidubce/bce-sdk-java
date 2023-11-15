/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.bcd.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RenewDomainResponse extends AbstractBceResponse {
    private String bceOrderId;
    private Boolean renewResult;

    public String getBceOrderId() {
        return bceOrderId;
    }

    public void setBceOrderId(String bceOrderId) {
        this.bceOrderId = bceOrderId;
    }

    public Boolean getRenewResult() {
        return renewResult;
    }

    public void setRenewResult(Boolean renewResult) {
        this.renewResult = renewResult;
    }
}
