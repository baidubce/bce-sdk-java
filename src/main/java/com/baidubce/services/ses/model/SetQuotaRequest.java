/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
 * Request object to set quota. It contains lots of parameters to set.
 */
public class SetQuotaRequest extends SesRequest {
    /**
     * The max count of sending email in 24 hours
     */
    private String maxPerDay;
    /**
     * The max count of sending email per second
     */
    private String maxPerSecond;

    public String getMaxPerDay() {
        return maxPerDay;
    }

    public void setMaxPerDay(String maxPerDay) {
        this.maxPerDay = maxPerDay;
    }

    public String getMaxPerSecond() {
        return maxPerSecond;
    }

    public void setMaxPerSecond(String maxPerSecond) {
        this.maxPerSecond = maxPerSecond;
    }

    @Override
    public String toString() {
        return "SetQuotaRequest [maxPerDay=" + maxPerDay + ", maxPerSecond=" + maxPerSecond + "]";
    }

}
