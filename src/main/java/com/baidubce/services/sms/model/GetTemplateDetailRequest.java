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
package com.baidubce.services.sms.model;

public class GetTemplateDetailRequest extends SmsRequest {
    /**
     * The URL parameter<br>
     * The ID of message template. It's unique, and it's pattern like this:smsTpl:6nHdNumZ4ZtGaKO. You have to do
     * URLEncode before using it.
     */
    private String templateId;

    public GetTemplateDetailRequest withTemplateId(String templateId) {
        setTemplateId(templateId);
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Override
    public String toString() {
        return "GetTemplateDetailRequest [templateId=" + templateId + "]";
    }

}
