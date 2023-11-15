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
package com.baidubce.services.tsdb.utils;


import static com.baidubce.services.tsdb.TsdbConstants.QuotaUnit.INGEST_QUOTA_UNIT;

/**
 * Quota calculator for test.
 *
 * @author guosiyue (guosiyue@baidu.com)
 */
public class QuotaCalculator {

    public static long getTimeSeriesQuota(long ingestDataPointsMonthlyQuota) {

        if (ingestDataPointsMonthlyQuota <= INGEST_QUOTA_UNIT) {
            return 1000;
        }
        if (ingestDataPointsMonthlyQuota <= INGEST_QUOTA_UNIT * 10) {
            return 10000;
        }
        if (ingestDataPointsMonthlyQuota <= INGEST_QUOTA_UNIT * 100) {
            return 100000;
        }
        if (ingestDataPointsMonthlyQuota <= INGEST_QUOTA_UNIT * 1000) {
            return 1000000;
        }
        return 10000000;
    }

}
