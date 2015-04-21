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

import java.util.List;

public class SendMessageRequest extends SmsRequest {
    /**
     * The ID of message template. It is unique, whose pattern like this: smsTpl:6nHdNumZ4ZtGaKO.
     */
    private String templateId;
    /**
     * The receiver of message.<br>
     * JSON format, like this:["13800238000","13800138001"]
     */
    private List<String> receiver;
    /**
     * The content variable of message.<br>
     * JSON format, like this: {\"key1\" : \"val1\", \"key2\" : \"val2\"}.
     */
    private String contentVar;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public List<String> getReceiver() {
        return receiver;
    }

    public void setReceiver(List<String> receiver) {
        this.receiver = receiver;
    }

    public String getContentVar() {
        return contentVar;
    }

    public void setContentVar(String contentVar) {
        this.contentVar = contentVar;
    }

    @Override
    public String toString() {
        return "SendMessageRequest [templateId=" + templateId + ", receiver=" + receiver + ", contentVar=" + contentVar
                + "]";
    }

}
