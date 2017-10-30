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
    private boolean backup;
    private boolean follow302;
    
    public OriginPeer withPeer(String peer) {
        this.peer = peer;
        return this;
    }
    
    public OriginPeer withHost(String host) {
        this.host = host;
        return this;
    }
    
    public OriginPeer withBackup(boolean backup) {
        this.backup = backup;
        return this;
    }
    
    public OriginPeer withFollow302(boolean follow302) {
        this.follow302 = follow302;
        return this;
    }

    public String getPeer() {
        return peer;
    }

    public void setPeer(String peer) {
        this.peer = peer;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isBackup() {
        return backup;
    }

    public void setBackup(boolean backup) {
        this.backup = backup;
    }

    public boolean isFollow302() {
        return follow302;
    }

    public void setFollow302(boolean follow302) {
        this.follow302 = follow302;
    }
}