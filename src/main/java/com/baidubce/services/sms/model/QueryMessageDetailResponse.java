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
import java.util.List;

import com.baidubce.services.sms.SmsConstant;
import com.fasterxml.jackson.annotation.JsonFormat;

public class QueryMessageDetailResponse extends SmsResponse {
    /**
     * The serial number of message
     */
    private String messageId;
    /**
     * The content of message to send
     */
    private String content;
    /**
     * The receiver of message.<br>
     * JSON format, like this:["13800238000","13800138001"]
     */
    private List<String> receiver;
    /**
     * The send time of message template.<br>
     * It must be conform to the standard of SMS API, like this: 2014-06-12T10:08:22Z
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SmsConstant.DEFAULT_DATETIME_FORMAT, timezone = "UTC")
    private Date sendTime;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getReceiver() {
        return receiver;
    }

    public void setReceiver(List<String> receiver) {
        this.receiver = receiver;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "QueryMessageDetailResponse [messageId=" + messageId + ", content=" + content + ", receiver=" + receiver
                + ", sendTime=" + sendTime + "]";
    }

}
