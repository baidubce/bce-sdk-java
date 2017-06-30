/*
 * Copyright 2016 Baidu, Inc.
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

package com.baidubce.services.cdn.model;

/**
 * @author yixing
 *
 */
public class OriginPeer extends JsonObject {
    private String peer;
    private String host;
    
    /**
     * @param peer
     * @return returns this object
     */
    public OriginPeer withPeer(String peer) {
        this.peer = peer;
        return this;
    }
    
    /**
     * @param host
     * @return returns this object
     */
    public OriginPeer withHost(String host) {
        this.host = host;
        return this;
    }

    /**
     * @return peer
     */
    public String getPeer() {
        return peer;
    }

    /**
     * @param peer
     */
    public void setPeer(String peer) {
        this.peer = peer;
    }
    
    /**
     * @return host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host
     */
    public void setHost(String host) {
        this.host = host;
    }
}
