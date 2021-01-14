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
public class GetTemplateResponse extends SmsResponse {

    /**
     * The unique code identifying the template
     */
    private String templateId;

    /**
     * Owner's Baidu Cloud account id of the template
     */
    private String userId;

    /**
     * Template's name
     */
    private String name;

    /**
     * Text content of the template
     */
    private String content;

    /**
     * The countryType indicates the countries or regions in which the template can be used.<br/>
     */
    private String countryType;

    /**
     * The sms type of the template content
     */
    private String smsType;

    /**
     * Approval status of the template
     */
    private String status;

    /**
     * Description of the template
     */
    private String description;

    /**
     * Review opinion of the template
     */
    private String review;

    /**
     * The create date of the template.<br/>
     * Its format must conform to the standard of SMS API, like this: 2014-06-12T10:08:22Z
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SmsConstant.DEFAULT_DATETIME_FORMAT, timezone = "UTC")
    private Date createDate;

    /**
     * The update date of the template.<br/>
     * Its format must conform to the standard of SMS API, like this: 2014-06-12T10:08:22Z
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SmsConstant.DEFAULT_DATETIME_FORMAT, timezone = "UTC")
    private Date updateDate;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setCountryType(String countryType) {
        this.countryType = countryType;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public void setCreateDate(Date date) {
        this.createDate = date;
    }

    public void setUpdateDate(Date date) {
        this.updateDate = date;
    }

    public String getUserId() {
        return userId;
    }

    public String getStatus() {
        return status;
    }

    public String getReview() {
        return review;
    }

    public String getCountryType() {
        return countryType;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getSmsType() {
        return smsType;
    }

    public String getTemplateId() {
        return templateId;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    @Override
    public String toString() {
        return "GetTemplateResponse [templateId=" + templateId
                + ", userId=" + userId
                + ", name=" + name
                + ", content=" + content
                + ", countryType=" + countryType
                + ", smsType=" + smsType
                + ", status=" + status
                + ", description=" + description
                + ", review=" + review
                + ", createDate=" + createDate
                + ", updateDate=" + updateDate + "]";
    }
}
