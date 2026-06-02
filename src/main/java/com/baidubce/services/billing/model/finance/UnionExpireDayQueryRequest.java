// Copyright (C) 2025 Baidu Inc. All rights reserved.

package com.baidubce.services.billing.model.finance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;

/**
 * @author Sun Qiming(sunqiming01@baidu.com).
 */
@Data
public class UnionExpireDayQueryRequest extends AbstractBceRequest {
    private String queryAccountId;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
