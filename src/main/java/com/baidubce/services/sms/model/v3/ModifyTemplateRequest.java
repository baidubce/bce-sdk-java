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

public class ModifyTemplateRequest extends SmsRequest {

    /**
     * The unique code identifying the template
     */
    private String templateId;

    /**
     * Name of the template
     */
    private String name;

    /**
     * Text content of the template
     */
    private String content;

    /**
     * The countryType indicates the countries or regions in which the template can be used.
     */
    private String countryType;

    /**
     * The sms type of the template content
     */
    private String smsType;

    /**
     * User-defined text description of the signature
     */
    private String description;

    public String getTemplateId() {
        return this.templateId;
    }

    public String getContent() {
        return content;
    }

    public String getCountryType() {
        return countryType;
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

    public void setContent(String content) {
        this.content = content;
    }

    public void setCountryType(String countryType) {
        this.countryType = countryType;
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

    public ModifyTemplateRequest withSmsType(String smsType) {
        this.setSmsType(smsType);
        return this;
    }

    public ModifyTemplateRequest withName(String name) {
        this.setName(name);
        return this;
    }

    public ModifyTemplateRequest withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public ModifyTemplateRequest withCountryType(String countryType) {
        this.setCountryType(countryType);
        return this;
    }

    public ModifyTemplateRequest withContent(String content) {
        this.setContent(content);
        return this;
    }

    public ModifyTemplateRequest withTemplateId(String templateId) {
        this.setTemplateId(templateId);
        return this;
    }

}
