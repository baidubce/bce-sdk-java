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
package com.baidubce.services.sms.model;

/**
 * Item that contains the response code and message of each mobile phone in the sending request
 */
public class SendMessageItem {

    /**
     * The response code corresponding to the mobile
     */
    private String code;

    /**
     * The target mobile
     */
    private String mobile;

    /**
     * The unique id identifying the message
     */
    private String messageId;

    /**
     * The explain message of the response
     */
    private String message;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return this.mobile;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"code\":\"").append(code).append("\",");
        sb.append("\"message\":\"").append(message).append("\",");
        sb.append("\"mobile\":\"").append(mobile).append("\",");
        sb.append("\"messageId\":\"").append(messageId).append("\"");
        sb.append("}");
        return sb.toString();
    }

}
