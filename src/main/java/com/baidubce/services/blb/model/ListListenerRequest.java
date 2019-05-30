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
package com.baidubce.services.blb.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.ListRequest;

/**
 * The request for list listener.
 */
public class ListListenerRequest extends ListRequest {

    /**
     * the blb id of listener.
     */
    private String blbId;
    /**
     * the port of the listener.
     */
    private int listenerPort;
    /**
     * the type of the listener.
     */
    private String type;

    public ListListenerRequest() {
    }

    public ListListenerRequest withBlbId(String blbId) {
        this.setBlbId(blbId);
        return this;
    }

    public ListListenerRequest withListenerPort(int listenerPort) {
        this.setListenerPort(listenerPort);
        return this;
    }

    public ListListenerRequest withType(String type) {
        this.setType(type);
        return this;
    }

    public ListListenerRequest(String blbId, int listenerPort, String type) {
        this.blbId = blbId;
        this.listenerPort = listenerPort;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBlbId() {
        return blbId;
    }

    public void setBlbId(String blbId) {
        this.blbId = blbId;
    }

    public int getListenerPort() {
        return listenerPort;
    }

    public void setListenerPort(int listenerPort) {
        this.listenerPort = listenerPort;
    }

    @Override
    public ListListenerRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
