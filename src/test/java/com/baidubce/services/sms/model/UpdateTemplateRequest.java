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

import com.baidubce.services.ses.model.SesRequest;

public class UpdateTemplateRequest extends SesRequest {
    /**
     * The URL parameter. <br>
     * The id of message template, it is unique in whole situation. <br>
     * For example:smsTpl:6nHdNumZ4ZtGaKO. <br>
     * It must do <code>URLencode</code> before using it.
     */
    private String templateId;

    /**
     * The name of message template<br>
     * It is unique, and it's max length is 32 chars.
     */
    private String name;

    /**
     * The content of message template.<br>
     * It's max length is 70 chars, and it's pattern is similar to ${KEY}, which key is the name of variable<br>
     * You can make a definition as you need, but it should be brief as possible as you can.
     */
    private String content;

    /**
     * The status of message template.<br>
     * It is a ENUM, and it's optional value is
     * <ul>
     * processing
     * </ul>
     * <ul>
     * valid
     * </ul>
     * <ul>
     * unvalid
     * </ul>
     * 
     * @see com.baidubce.services.sms.model.TemplateStatus
     */
    private String status;

    public UpdateTemplateRequest withTemplateId(String templateId) {
        setTemplateId(templateId);
        return this;
    }

    public UpdateTemplateRequest withName(String name) {
        this.setName(name);
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EditTemplateRequest [templateId=" + templateId + ", name=" + name + ", content=" + content
                + ", status=" + status + "]";
    }

}
