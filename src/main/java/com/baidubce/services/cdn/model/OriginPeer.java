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
 */
public class OriginPeer {
    private String peer;
    private String host;
    private boolean backup;
    private int weight;
    private String isp;

    public OriginPeer withPeer(String peer) {
        this.peer = peer;
        return this;
    }

    public OriginPeer withIsp(String isp) {
        this.isp = isp;
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

    public OriginPeer withWeight(int weight) {
        this.weight = weight;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }
}