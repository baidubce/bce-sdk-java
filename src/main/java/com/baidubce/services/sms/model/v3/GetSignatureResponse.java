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

import com.baidubce.services.sms.SmsConstant;
import com.baidubce.services.sms.model.SmsResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSignatureResponse extends SmsResponse {
    /**
     * The unique code identifying the signature
     */
    private String signatureId;

    /**
     * Owner's Baidu Cloud account id of the signature
     */
    private String userId;

    /**
     * Text content of the signature
     */
    private String content;

    /**
     * Type of the signature
     */
    private String contentType;

    /**
     * Approval status of the signature
     */
    private String status;

    /**
     * CountryType indicates the countries or regions in which the signature can be used.
     */
    private String countryType;

    /**
     * Review opinion of the signature
     */
    private String review;

    /**
     * The create date of the signature.<br/>
     * Its format must conform to the standard of SMS API, like this: 2014-06-12T10:08:22Z
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SmsConstant.DEFAULT_DATETIME_FORMAT, timezone = "UTC")
    private Date createDate;

    /**
     * The update date of the signature.<br/>
     * Its format must conform to the standard of SMS API, like this: 2014-06-12T10:08:22Z
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SmsConstant.DEFAULT_DATETIME_FORMAT, timezone = "UTC")
    private Date updateDate;

    public String getContent() {
        return content;
    }

    public String getContentType() {
        return contentType;
    }

    public String getCountryType() {
        return countryType;
    }

    public String getReview() {
        return review;
    }

    public String getSignatureId() {
        return signatureId;
    }

    public String getStatus() {
        return status;
    }

    public String getUserId() {
        return userId;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setCountryType(String countryType) {
        this.countryType = countryType;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setSignatureId(String signatureId) {
        this.signatureId = signatureId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCreateDate(Date date) {
        this.createDate = date;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "GetSignatureResponse [signatureId=" + signatureId
                + ", userId=" + userId
                + ", content=" + content
                + ", contentType=" + contentType
                + ", status=" + status
                + ", countryType=" + countryType
                + ", review=" + review
                + ", createDate=" + createDate
                + ", updateDate=" + updateDate + "]";
    }
}
