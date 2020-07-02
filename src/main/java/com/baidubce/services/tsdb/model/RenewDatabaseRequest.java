/*
 * Copyright 2019 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.tsdb.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;

/**
 * Renew database request model.
 */
@Data
public class RenewDatabaseRequest extends AbstractBceRequest {

    /**
     * Required.
     */
    private String databaseId;

    /**
     * Required.
     * The renew length. unit: month.
     */
    private Integer purchaseLength;

    /**
     * Optional.
     */
    private String couponName;

    public RenewDatabaseRequest withDatabaseId(String databaseId) {
        this.databaseId = databaseId;
        return this;
    }

    public RenewDatabaseRequest withPurchaseLength(Integer purchaseLength) {
        this.purchaseLength = purchaseLength;
        return this;
    }

    public RenewDatabaseRequest withCouponName(String couponName) {
        this.couponName = couponName;
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
