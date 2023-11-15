/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.bcd.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterDomainResponse extends AbstractBceResponse {
    private String bceOrderId;
    private String domain;
    private boolean registerResult;

    public String getBceOrderId() {
        return bceOrderId;
    }

    public void setBceOrderId(String bceOrderId) {
        this.bceOrderId = bceOrderId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public boolean isRegisterResult() {
        return registerResult;
    }

    public void setRegisterResult(boolean registerResult) {
        this.registerResult = registerResult;
    }
}
