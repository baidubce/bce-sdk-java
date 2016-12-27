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

public class CreateTemplateRequest extends SmsRequest {

    /**
     * signature invokeId, can obtained from console(cloud.baidu.com)
     */
    private String invokeId;

    /**
     * optional;
     * assigned only one signature have different profile
     */
    private String profileId;

    /**
     * The name of message template<br>
     * It's max length is 32 chars, not repeat.
     */
    private String name;

    /**
     * The content of message template<br>
     * It's max length is 300 chars, whose pattern like this:${KEY}, which key is the name of variable. You can make
     * defined as you need, but should be brief as possible as you can.
     */
    private String content;

    public CreateTemplateRequest withInvokeId(String invokeId) {
        setInvokeId(invokeId);
        return this;
    }

    public CreateTemplateRequest withProfileId(String profileId) {
        setProfileId(profileId);
        return this;
    }

    public CreateTemplateRequest withName(String name) {
        setName(name);
        return this;
    }

    public CreateTemplateRequest withContent(String content) {
        setContent(content);
        return this;
    }

    public String getInvokeId() {
        return invokeId;
    }

    public void setInvokeId(String invokeId) {
        this.invokeId = invokeId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
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
}
