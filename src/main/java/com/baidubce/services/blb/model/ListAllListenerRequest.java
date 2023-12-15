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
 * The request for list blb's all listeners.
 */
public class ListAllListenerRequest extends ListRequest {

    /**
     * the blb id of listener.
     */
    private String blbId;
    /**
     * the port of the listener.
     */
    private int listenerPort;

    public ListAllListenerRequest() {
    }

    public ListAllListenerRequest withBlbId(String blbId) {
        this.setBlbId(blbId);
        return this;
    }

    public ListAllListenerRequest withListenerPort(int listenerPort) {
        this.setListenerPort(listenerPort);
        return this;
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
    public ListAllListenerRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
