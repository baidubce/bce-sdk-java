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

public class ListBlackRequest extends SmsRequest {

    /**
     * type of black
     * Support multiple mobile phone numbers, up to 200 maximum, separated by comma.
     */
    private String phone;

    /**
     * smsType
     */
    private String smsType;

    /**
     * string-based Signature ID
     */
    private String signatureIdStr;

    /**
     * the start of time condition
     * format is yyyy-MM-dd
     */
    private String startTime;

    /**
     * the end of time condition
     * format is yyyy-MM-dd
     */
    private String endTime;

    /**
     * The current page number
     */
    private Integer pageNo = 1;
    /**
     * The current page size, range from 1 to 99999
     */
    private Integer pageSize = 10;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
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

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSignatureIdStr() {
        return signatureIdStr;
    }

    public void setSignatureIdStr(String signatureIdStr) {
        this.signatureIdStr = signatureIdStr;
    }
}
