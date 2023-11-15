/*
 * Copyright (c) 2023 Baidu.com, Inc. All Rights Reserved
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

public class ListStatisticsRequest extends SmsRequest {
    /**
     * Short message type, required.<br/>
     * Default: "all".
     */
    private String smsType = "all";

    /**
     * The ID of message signature, optional.
     */
    private String signatureId;

    /**
     * Template code, optional.<br/>
     * Example: sms-tmpl-xxxxxxxx.
     */
    private String templateCode;

    /**
     * The countryType indicates the countries or regions in which the signature can be used.<br/>
     * The value of countryType could be "domestic", "international".<br/>
     * "domestic" means the short messages sent in Mainland China.<br/>
     * "international" means the short messages sent outside Mainland China.<br/>
     */
    private String countryType;

    /**
     * The start of time condition<br/>
     * Format: yyyy-MM-dd
     */
    private String startTime;

    /**
     * The end of time condition<br/>
     * Format is yyyy-MM-dd
     */
    private String endTime;


    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getCountryType() {
        return countryType;
    }

    public void setCountryType(String countryType) {
        this.countryType = countryType;
    }

    public String getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(String signatureId) {
        this.signatureId = signatureId;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
