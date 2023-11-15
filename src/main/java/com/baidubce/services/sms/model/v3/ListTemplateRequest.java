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

public class ListTemplateRequest extends SmsRequest {
    /**
     * The name of the template
     */
    private String name;

    /**
     * The content of the template
     */
    private String content;

    /**
     * The unique id of the template
     */
    private String templateId;

    /**
     * The sms type of the template content <br/>
     * All the smsTypes can be obtained from cloud.baidu.com
     */
    private String smsType;

    /**
     * The status of the template
     */
    private String status;

    /**
     * The countryType indicates the countries or regions in which the template can be used.<br/>
     * The value of countryType could be DOMESTIC or INTERNATIONAL or GLOBAL.<br/>
     * DOMESTIC means the template can only be used in Mainland China;<br/>
     * INTERNATIONAL means the template can only be used out of Mainland China;<br/>
     * GLOBAL means the template can only be used all over the world.
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
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
