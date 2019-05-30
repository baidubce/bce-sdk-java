/*
 * Copyright 2018-2019 Baidu, Inc.
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

/**
 * Represent the request for creating database.
 */
public class CreateDatabaseRequest extends AbstractBceRequest {
    /**
     * Required.
     * The database name which should only contain 6 to 40 lowercase letters or digits.
     */
    private String databaseName;

    /**
     * Optional.
     * The database's description.
     */
    private String description = "";

    /**
     * Required.
     * The database quota of ingestion data points per month.
     */
    private Integer ingestDataPointsMonthly;

    /**
     * Optional.
     * The database quota of query units per month.
     */
    private Integer queryUnitsMonthly;

    /**
     * Optional.
     * The store bytes quota. unit: GB
     */
    private Integer storeBytesQuota;

    /**
     * Required.
     * The purchase length.
     */
    private Integer purchaseLength;

    /**
     * Optional.
     * The coupon name.
     */
    private String couponName;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public Integer getIngestDataPointsMonthly() {
        return ingestDataPointsMonthly;
    }

    public void setIngestDataPointsMonthly(Integer ingestDataPointsMonthly) {
        this.ingestDataPointsMonthly = ingestDataPointsMonthly;
    }

    public Integer getQueryUnitsMonthly() {
        return queryUnitsMonthly;
    }

    public void setQueryUnitsMonthly(Integer queryUnitsMonthly) {
        this.queryUnitsMonthly = queryUnitsMonthly;
    }

    public Integer getStoreBytesQuota() {
        return storeBytesQuota;
    }

    public void setStoreBytesQuota(Integer storeBytesQuota) {
        this.storeBytesQuota = storeBytesQuota;
    }

    public Integer getPurchaseLength() {
        return purchaseLength;
    }

    public void setPurchaseLength(Integer purchaseLength) {
        this.purchaseLength = purchaseLength;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public CreateDatabaseRequest withDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    public CreateDatabaseRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    public CreateDatabaseRequest withIngestDataPointsMonthly(Integer ingestDataPointsMonthly) {
        this.ingestDataPointsMonthly = ingestDataPointsMonthly;
        return this;
    }

    public CreateDatabaseRequest withQueryUnitsMonthly(Integer queryUnitsMonthly) {
        this.queryUnitsMonthly = queryUnitsMonthly;
        return this;
    }

    public CreateDatabaseRequest withStoreBytesQuota(Integer storeBytesQuota) {
        this.storeBytesQuota = storeBytesQuota;
        return this;
    }

    public CreateDatabaseRequest withPurchaseLength(Integer purchaseLength) {
        this.purchaseLength = purchaseLength;
        return this;
    }

    public CreateDatabaseRequest withCouponName(String couponName) {
        this.couponName = couponName;
        return this;
    }

    @Override
    public CreateDatabaseRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
