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

import static com.baidubce.services.tsdb.TsdbConstants.QuotaUnit.ADDITIONAL_TIMESERIES_QUOTA;
import static com.baidubce.services.tsdb.TsdbConstants.QuotaUnit.INGEST_QUOTA_UNIT;
import static com.baidubce.services.tsdb.TsdbConstants.QuotaUnit.QUERY_QUOTA_UNIT;
import static com.baidubce.services.tsdb.TsdbConstants.QuotaUnit.STORAGE_QUOTA_UNIT;
import static com.baidubce.util.Validate.checkIsTrue;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.tsdb.utils.QuotaCalculator;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * Resize database request model.
 */
@Data
@Builder
public class ResizeDatabaseRequest extends AbstractBceRequest {

    /**
     * Required.
     */
    @NonNull
    private String databaseId;

    /**
     * Required.
     * The database quota of ingestion data points per month.
     * Must be multiple of INGEST_QUOTA_UNIT.
     */
    private long ingestDataPointsMonthly;

    /**
     * Required.
     * The database quota of query units per month.
     * Must be multiple of QUERY_QUOTA_UNIT.
     */
    private long queryUnitsMonthly;

    /**
     * Required.
     * The store bytes quota.
     * Must be multiple of STORAGE_QUOTA_UNIT.
     */
    private long storeBytesQuota;

    /**
     * Required.
     * The database quota of time series. When ingest quota <= 1000 * INGEST_QUOTA_UNIT, timeSeriesQuota should be
     * consistent with the result of the {@link QuotaCalculator}. And if ingest quota > 1000 * INGEST_QUOTA_UNIT, you
     * can purchase additional quota by multiple of ADDITIONAL_TIMESERIES_QUOTA.
     */
    private long timeSeriesQuota;

    /**
     * Required.
     * The length limit multiple quota.
     * Default limit of String/BigDecimal value is not more than 256 characters, and Byte value should not exceed
     * 200 bytes for free (lengthLimitMultipleQuota = 1), you can purchase additional quota by multiple.
     */
    private int lengthLimitMultipleQuota;

    /**
     * Optional.
     */
    private String couponName;

    public ResizeDatabaseRequest(String databaseId, long ingestDataPointsMonthly, long queryUnitsMonthly,
            long storeBytesQuota, long timeSeriesQuota, int lengthLimitMultipleQuota, String couponName) {
        checkIsTrue(ingestDataPointsMonthly > 0 && ingestDataPointsMonthly % INGEST_QUOTA_UNIT == 0,
                "Illegal quota of ingest, must be greater than 0 and be multiple of INGEST_QUOTA_UNIT(1 million).");
        checkIsTrue(queryUnitsMonthly >= 10 * QUERY_QUOTA_UNIT && queryUnitsMonthly % QUERY_QUOTA_UNIT == 0,
                "Illegal quota of query, must be not less than 100 thousand and be multiple of " +
                        "QUERY_QUOTA_UNIT(100 thousand).");
        checkIsTrue(storeBytesQuota >= 0 && storeBytesQuota % STORAGE_QUOTA_UNIT == 0,
                "Illegal quota of storage, must be non-negative and be multiple of STORAGE_QUOTA_UNIT(1G).");
        // Check time series quota.
        if (timeSeriesQuota <= 0) {
            timeSeriesQuota = QuotaCalculator.getTimeSeriesQuota(ingestDataPointsMonthly);
        }
        if (ingestDataPointsMonthly < 1000 * INGEST_QUOTA_UNIT) {
            checkIsTrue(timeSeriesQuota == QuotaCalculator.getTimeSeriesQuota(ingestDataPointsMonthly),
                    String.format("Illegal quota of timeseries, must be consistent with the result of the " +
                                    "QuotaCalculator, excepted %s but got %s.",
                            QuotaCalculator.getTimeSeriesQuota(ingestDataPointsMonthly), timeSeriesQuota));
        } else {
            checkIsTrue(timeSeriesQuota >= QuotaCalculator.getTimeSeriesQuota(ingestDataPointsMonthly) &&
                            timeSeriesQuota % ADDITIONAL_TIMESERIES_QUOTA == 0,
                    "Illegal quota of timeseries, must be multiple of ADDITIONAL_TIMESERIES_QUOTA(1 million.");
        }
        // Check length limit multiple quota.
        if (storeBytesQuota > 0 && lengthLimitMultipleQuota == 0) {
            lengthLimitMultipleQuota = 1;
        }
        checkIsTrue(storeBytesQuota == 0 && lengthLimitMultipleQuota == 0 ||
                        storeBytesQuota > 0 && lengthLimitMultipleQuota > 0,
                "Illegal quota of lengthLimitMultipleQuota, must be consistent with the storage bytes quota.");
        this.databaseId = databaseId;
        this.ingestDataPointsMonthly = ingestDataPointsMonthly;
        this.queryUnitsMonthly = queryUnitsMonthly;
        this.storeBytesQuota = storeBytesQuota;
        this.timeSeriesQuota = timeSeriesQuota;
        this.lengthLimitMultipleQuota = lengthLimitMultipleQuota;
        this.couponName = couponName;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
