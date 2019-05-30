/*
 * Copyright 2019 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law * or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.binaryparser.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * Response model for binary parser service
 * Created by yuanyoujun on 2017/9/2.
 */
public class BinaryParser extends AbstractBceResponse {
    private String uuid;
    private String name;
    private String endpoint;
    private String inputTopic;
    private String script;
    private String outputTopic;
    private String outputRuleid;
    private Format format;
    private String errorEvent;
    private String errorTime;

    public String getOutputRuleid() {
        return outputRuleid;
    }

    public void setOutputRuleid(String outputRuleid) {
        this.outputRuleid = outputRuleid;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public String getErrorEvent() {
        return errorEvent;
    }

    public void setErrorEvent(String errorEvent) {
        this.errorEvent = errorEvent;
    }

    public String getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(String errorTime) {
        this.errorTime = errorTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getInputTopic() {
        return inputTopic;
    }

    public void setInputTopic(String inputTopic) {
        this.inputTopic = inputTopic;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getOutputTopic() {
        return outputTopic;
    }

    public void setOutputTopic(String outputTopic) {
        this.outputTopic = outputTopic;
    }
}
