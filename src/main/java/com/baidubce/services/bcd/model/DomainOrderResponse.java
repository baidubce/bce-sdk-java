/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.bcd.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainOrderResponse extends AbstractBceResponse {
    private String bceOrderId;

    public String getBceOrderId() {
        return bceOrderId;
    }

    public void setBceOrderId(String bceOrderId) {
        this.bceOrderId = bceOrderId;
    }
}
