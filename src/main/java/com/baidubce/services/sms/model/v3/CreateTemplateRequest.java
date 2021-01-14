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

public class CreateTemplateRequest extends SmsRequest {

    /**
     * Template's name
     */
    private String name;

    /**
     * The content of template
     * e.g. Your verify code is ${code}, it will be expired in ${number} minutes.
     */
    private String content;

    /**
     * The sms type of the template content <br/>
     * All the sms types can be obtained from cloud.baidu.com
     */
    private String smsType;

    /**
     * The countryType indicates the countries or regions in which the template can be used.<br/>
     * The value of countryType could be DOMESTIC or INTERNATIONAL or GLOBAL.<br/>
     * DOMESTIC means the template can only be used in Mainland China;<br/>
     * INTERNATIONAL means the template can only be used out of Mainland China;<br/>
     * GLOBAL means the template can only be used all over the world.
     */
    private String countryType;

    /**
     * Description of the template
     */
    private String description;

    public void setName(String name) {
        this.name = name;
    }

    public CreateTemplateRequest withName(String name) {
        this.setName(name);
        return this;
    }

    public String getName() {
        return this.name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CreateTemplateRequest withContent(String content) {
        this.setContent(content);
        return this;
    }

    public String getContent() {
        return this.content;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public CreateTemplateRequest withSmsType(String smsType) {
        this.setSmsType(smsType);
        return this;
    }

    public String getSmsType() {
        return this.smsType;
    }

    public void setCountryType(String countryType) {
        this.countryType = countryType;
    }

    public CreateTemplateRequest withCountryType(String countryType) {
        this.setCountryType(countryType);
        return this;
    }

    public String getCountryType() {
        return this.countryType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CreateTemplateRequest withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public String getDescription() {
        return this.description;
    }
}
