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

public class ListSignatureRequest extends SmsRequest {

    /**
     * The text content of the signature
     */
    private String content;

    /**
     * The unique id of the signature
     */
    private String signatureId;

    /**
     * The status of the signature
     */
    private String status;

    /**
     * The countryType indicates the countries or regions in which the signature can be used.<br/>
     * The value of countryType could be DOMESTIC or INTERNATIONAL or GLOBAL.<br/>
     * DOMESTIC means the signature can only be used in Mainland China;<br/>
     * INTERNATIONAL means the signature can only be used out of Mainland China;<br/>
     * GLOBAL means the signature can only be used all over the world.
     */
    private String countryType;

    /**
     * The current page number
     */
    private Integer pageNo;

    /**
     * The current page size, range from 1 to 999
     */
    private Integer pageSize;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(String signatureId) {
        this.signatureId = signatureId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountryType() {
        return countryType;
    }

    public void setCountryType(String countryType) {
        this.countryType = countryType;
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
}
