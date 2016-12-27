/*
 * Copyright 2014 Baidu, Inc.
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

import java.util.Date;

import com.baidubce.services.sms.SmsConstant;
import com.fasterxml.jackson.annotation.JsonFormat;

public class GetTemplateDetailResponse extends SmsResponse {
    /**
     * The URL parameter<br>
     * The ID of message template. It's unique, and it's pattern like this:smsTpl:6nHdNumZ4ZtGaKO. You have to do
     * URLEncode before using it.
     */
    private String templateId;
    /**
     * The name of message template<br>
     * It's max length is 32 chars, not repeat.
     */
    private String name;

    /**
     * The content of message template<br>
     * It's max length is 70 chars, whose pattern like this:${KEY}, which key is the name of variable. You can make
     * defined as you need, but should be brief as possible as you can.
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

    /**
     * The create date of message template.<br>
     * It must be conform to the standard of SMS API, like this: 2014-06-12T10:08:22Z
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SmsConstant.DEFAULT_DATETIME_FORMAT, timezone = "UTC")
    private Date createTime;

    /**
     * The update date of message template.<br>
     * It must be conform to the standard of SMS API, like this: 2014-06-12T10:08:22Z
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SmsConstant.DEFAULT_DATETIME_FORMAT, timezone = "UTC")
    private Date updateTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "GetTemplateDetailResponse [templateId=" + templateId + ", name=" + name + ", content=" + content
                + ", status=" + status + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
    }

}
