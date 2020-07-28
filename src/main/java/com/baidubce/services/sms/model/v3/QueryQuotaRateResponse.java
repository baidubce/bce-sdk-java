/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.sms.model.v3;

import com.baidubce.services.sms.model.SmsResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryQuotaRateResponse extends SmsResponse {

    /**
     * Daily quota.<br/>
     * QuotaPerDay indicates the upper limit of the response-success request counts in one natural day.
     */
    private int quotaPerDay;

    /**
     * Monthly quota <br/>
     * QuotaPerDay indicates the upper limit of the response-success request counts in one natural month.
     */
    private int quotaPerMonth;

    /**
     * Remaining quota of this day
     */
    private int quotaRemainToday;

    /**
     * Remaining quota of this month
     */
    private int quotaRemainThisMonth;

    /**
     * QuotaWhitelist indicates a user is in quota white list or not. <br/>
     * If quotaWhitelist is true, SMS will skip counting quota when the user send SMS request
     */
    private boolean quotaWhitelist;

    /**
     * Daily rate limit with same mobile and signature
     */
    @JsonProperty("rateLimitPerMobilePerSignByDay")
    private int rateLimitPerDay;

    /**
     * Hourly limit of requests with same mobile and signature
     */
    @JsonProperty("rateLimitPerMobilePerSignByHour")
    private int rateLimitPerHour;

    /**
     * The limit of requests with same mobile and signature in one minute
     */
    @JsonProperty("rateLimitPerMobilePerSignByMinute")
    private int rateLimitPerMinute;

    /**
     * RateLimitWhitelist indicates a user is in rate-limit white list or not. <br/>
     * If rateLimitWhitelist is true, SMS will skip counting rate when the user sends SMS request
     */
    private boolean rateLimitWhitelist;

    public void setQuotaWhitelist(boolean quotaWhitelist) {
        this.quotaWhitelist = quotaWhitelist;
    }

    public void setRateLimitWhitelist(boolean rateLimitWhitelist) {
        this.rateLimitWhitelist = rateLimitWhitelist;
    }

    public void setRateLimitPerMinute(int rateLimitPerMinute) {
        this.rateLimitPerMinute = rateLimitPerMinute;
    }

    public void setRateLimitPerHour(int rateLimitPerHour) {
        this.rateLimitPerHour = rateLimitPerHour;
    }

    public void setRateLimitPerDay(int rateLimitPerDay) {
        this.rateLimitPerDay = rateLimitPerDay;
    }

    public void setQuotaPerMonth(int quotaPerMonth) {
        this.quotaPerMonth = quotaPerMonth;
    }

    public void setQuotaPerDay(int quotaPerDay) {
        this.quotaPerDay = quotaPerDay;
    }

    public void setQuotaRemainThisMonth(int quotaRemainThisMonth) {
        this.quotaRemainThisMonth = quotaRemainThisMonth;
    }

    public void setQuotaRemainToday(int quotaRemainToday) {
        this.quotaRemainToday = quotaRemainToday;
    }

    public int getRateLimitPerMinute() {
        return rateLimitPerMinute;
    }

    public int getRateLimitPerHour() {
        return rateLimitPerHour;
    }

    public int getRateLimitPerDay() {
        return rateLimitPerDay;
    }

    public int getQuotaPerMonth() {
        return quotaPerMonth;
    }

    public int getQuotaPerDay() {
        return quotaPerDay;
    }

    public int getQuotaRemainToday() {
        return quotaRemainToday;
    }

    public int getQuotaRemainThisMonth() {
        return quotaRemainThisMonth;
    }

    public boolean isQuotaWhitelist() {
        return quotaWhitelist;
    }

    public boolean isRateLimitWhitelist() {
        return rateLimitWhitelist;
    }

    @Override
    public String toString() {
        return "QueryQuotaRateResponse [quotaPerDay=" + quotaPerDay
                + ", quotaPerMonth=" + quotaPerMonth
                + ", quotaRemainToday=" + quotaRemainToday
                + ", quotaRemainThisMonth=" + quotaRemainThisMonth
                + ", quotaWhitelist=" + quotaWhitelist
                + ", rateLimitPerMobilePerSignByDay=" + rateLimitPerDay
                + ", rateLimitPerMobilePerSignByHour=" + rateLimitPerHour
                + ", rateLimitPerMobilePerSignByMinute=" + rateLimitPerMinute
                + ", rateLimitWhitelist=" + rateLimitWhitelist + "]";
    }
}
