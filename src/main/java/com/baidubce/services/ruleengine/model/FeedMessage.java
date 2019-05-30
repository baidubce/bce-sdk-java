/*
 * Copyright 2019 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law * or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.ruleengine.model;

/**
 * message model
 *
 * Created by huangjiatian on 2019/03/07.
 */
public class FeedMessage {

    private String topic;
    private String clientid;
    private String clientip;
    private byte[] payload;

    public FeedMessage(String payload) {
        this(payload, "", "", "");
    }

    public FeedMessage(String payload, String topic, String clientid, String clientip) {
        this.payload = payload.getBytes();
        this.topic = topic;
        this.clientid = clientid;
        this.clientip = clientip;
    }

    public FeedMessage(byte[] payload) {
        this(payload, "", "", "");
    }

    public FeedMessage(byte[] payload, String topic, String clientid, String clientip) {
        this.payload = payload;
        this.topic = topic;
        this.clientid = clientid;
        this.clientip = clientip;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getClientip() {
        return clientip;
    }

    public void setClientip(String clientip) {
        this.clientip = clientip;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }
}
