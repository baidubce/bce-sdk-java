/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.services.ses.model;

/**
 * Response object of getting quota. It contains lots of parameters of quota.
 */
public class GetQuotaResponse extends SesResponse {
    /**
     * The max count of sending email in 24 hours
     */
    private Integer maxPerDay;
    /**
     * The max count of sending email per second
     */
    private Integer maxPerSecond;
    /**
     * The count of sending email that is finished in last 24 hours
     */
    private Integer usedToday;

    public Integer getMaxPerDay() {
        return maxPerDay;
    }

    public void setMaxPerDay(Integer maxPerDay) {
        this.maxPerDay = maxPerDay;
    }

    public Integer getMaxPerSecond() {
        return maxPerSecond;
    }

    public void setMaxPerSecond(Integer maxPerSecond) {
        this.maxPerSecond = maxPerSecond;
    }

    public Integer getUsedToday() {
        return usedToday;
    }

    public void setUsedToday(Integer usedToday) {
        this.usedToday = usedToday;
    }

    @Override
    public String toString() {
        return "GetQuotaResponse [maxPerDay=" + maxPerDay + ", maxPerSecond=" + maxPerSecond + ", usedToday="
                + usedToday + "]";
    }

}
