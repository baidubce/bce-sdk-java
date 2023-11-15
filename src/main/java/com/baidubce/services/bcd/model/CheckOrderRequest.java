/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.bcd.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class CheckOrderRequest extends AbstractBceRequest {
    private String bceOrderId;

    public String getBceOrderId() {
        return bceOrderId;
    }

    public void setBceOrderId(String bceOrderId) {
        this.bceOrderId = bceOrderId;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
