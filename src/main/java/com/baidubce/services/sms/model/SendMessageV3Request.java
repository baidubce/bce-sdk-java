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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Request for sending message by SMS3.0
 */
public class SendMessageV3Request extends SmsRequest {

    /**
     * The target mobile phone number, use "," to split if you have multiple targets
     * e.g. 138001380000,138001380001
     */
    private String mobile;

    /**
     * The unique code identifying message content template, which can be obtained from cloud.baidu.com
     * e.g. sms-tmpl-xIEkZG87760
     */
    private String template;

    /**
     * The unique code identifying message content signature, which can be obtained from cloud.baidu.com
     * <p>Signature is generally the text in "【xxx】" in front of the message</p>
     * e.g. sms-sign-AfFLyb67322
     */
    private String signatureId;

    /**
     * ContentVar is a hash map which indicates the correspondence of template parameter name to parameter value.
     */
    private Map<String, String> contentVar;

    /**
     * optional;
     * The user self defined param
     */
    private String custom;

    /**
     * optional;
     * The user self defined channel code
     */
    private String userExtId;

    /**
     * optional;
     * The id of callback url specified by user
     */
    @JsonProperty("merchantUrlId")
    private String callbackUrlId;

    /**
     * optional;
     * The parameter for idempotence of http post
     */
    private String clientToken;


    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTemplate() {
        return this.template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(String signatureId) {
        this.signatureId = signatureId;
    }

    public Map<String, String> getContentVar() {
        return contentVar;
    }

    public void setContentVar(Map<String, String> contentVar) {
        this.contentVar = contentVar;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getUserExtId() {
        return userExtId;
    }

    public void setUserExtId(String userExtId) {
        this.userExtId = userExtId;
    }

    public String getClientToken() {
        return this.clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getCallbackUrlId() {
        return this.callbackUrlId;
    }

    public void setCallbackUrlId(String callbackUrlId) {
        this.callbackUrlId = callbackUrlId;
    }

}
