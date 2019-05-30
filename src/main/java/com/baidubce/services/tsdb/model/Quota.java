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

/**
 * Represent the quota for database.
 */
public class Quota {

    private Integer ingestDataPointsMonthly;

    private Integer queryUnitsMonthly;

    private Integer storeBytesQuota;

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

    public Integer getIngestDataPointsMonthly() {
        return ingestDataPointsMonthly;
    }

    public void setIngestDataPointsMonthly(Integer ingestDataPointsMonthly) {
        this.ingestDataPointsMonthly = ingestDataPointsMonthly;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Quota)) {
            return false;
        }

        Quota quota = (Quota) o;
        if (!integerEquals(ingestDataPointsMonthly, quota.getIngestDataPointsMonthly())
                || !integerEquals(queryUnitsMonthly, quota.getQueryUnitsMonthly())
                || !integerEquals(storeBytesQuota, quota.getStoreBytesQuota())) {
            return false;
        }
        return true;
    }

    private boolean integerEquals(Integer i1, Integer i2) {
        return i1 != null ? i1.equals(i2) : i2 == null;
    }
}
