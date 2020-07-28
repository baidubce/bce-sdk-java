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

import com.baidubce.services.sms.model.SmsRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateQuotaRateRequest extends SmsRequest {

    /**
     * Daily quota.<br/>
     * QuotaPerDay indicates the upper limit of the response-success request counts in one natural day.<br/>
     * Set quotaPerDay value to -1 if you want to ignore counting daily quota.
     */
    private Integer quotaPerDay;

    /**
     * Monthly quota <br/>
     * QuotaPerDay indicates the upper limit of the response-success request counts in one natural month.<br/>
     * Set quotaPerDay value to -1 if you want to ignore counting monthly quota.
     */
    private Integer quotaPerMonth;

    /**
     * Daily rate limit with same mobile and signature<br/>
     * Set rateLimitPerDay value to -1 if you want to ignore counting this rate.
     */
    @JsonProperty("rateLimitPerMobilePerSignByDay")
    private Integer rateLimitPerDay;

    /**
     * Hourly limit of requests with same mobile and signature<br/>
     * Set rateLimitPerDay value to -1 if you want to ignore counting this rate.
     */
    @JsonProperty("rateLimitPerMobilePerSignByHour")
    private Integer rateLimitPerHour;

    /**
     * The limit of requests with same mobile and signature in one minute<br/>
     * Set rateLimitPerDay value to -1 if you want to ignore counting this rate.
     */
    @JsonProperty("rateLimitPerMobilePerSignByMinute")
    private Integer rateLimitPerMinute;

    public Integer getQuotaPerDay() {
        return quotaPerDay;
    }

    public Integer getQuotaPerMonth() {
        return quotaPerMonth;
    }

    public Integer getRateLimitPerDay() {
        return rateLimitPerDay;
    }

    public Integer getRateLimitPerHour() {
        return rateLimitPerHour;
    }

    public Integer getRateLimitPerMinute() {
        return rateLimitPerMinute;
    }

    public void setQuotaPerDay(Integer quotaPerDay) {
        this.quotaPerDay = quotaPerDay;
    }

    public void setQuotaPerMonth(Integer quotaPerMonth) {
        this.quotaPerMonth = quotaPerMonth;
    }

    public void setRateLimitPerDay(Integer rateLimitPerDay) {
        this.rateLimitPerDay = rateLimitPerDay;
    }

    public void setRateLimitPerHour(Integer rateLimitPerHour) {
        this.rateLimitPerHour = rateLimitPerHour;
    }

    public void setRateLimitPerMinute(Integer rateLimitPerMinute) {
        this.rateLimitPerMinute = rateLimitPerMinute;
    }

    public UpdateQuotaRateRequest withQuotaPerDay(Integer quotaPerDay) {
        this.setQuotaPerDay(quotaPerDay);
        return this;
    }

    public UpdateQuotaRateRequest withQuotaPerMonth(Integer quotaPerMonth) {
        this.setQuotaPerMonth(quotaPerMonth);
        return this;
    }

    public UpdateQuotaRateRequest withRateLimitPerDay(Integer rateLimitPerDay) {
        this.setRateLimitPerDay(rateLimitPerDay);
        return this;
    }

    public UpdateQuotaRateRequest withRateLimitPerHour(Integer rateLimitPerHour) {
        this.setRateLimitPerHour(rateLimitPerHour);
        return this;
    }

    public UpdateQuotaRateRequest withRateLimitPerMinute(Integer rateLimitPerMinute) {
        this.setRateLimitPerMinute(rateLimitPerMinute);
        return this;
    }
}
