/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.cnap.model.access;

/**
 * The model for access.
 */
public class NewAccessModel {

    /**
     * The name of access.
     */
    private String name;

    /**
     * The type of access.
     * 1 indicate access in cluster network.
     * 2 indicate access in vpc network.
     * 3 indicate access in public network.
     */
    private int type = 3;

    /**
     * The protocol of access.
     * eg TCP or UDP.
     */
    private String protocol = "TCP";


    /**
     * The port of access.
     */
    private int port;

    /**
     * The port of listening.
     */
    private int targetPort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTargetPort() {
        return targetPort;
    }

    public void setTargetPort(int targetPort) {
        this.targetPort = targetPort;
    }

    public NewAccessModel withType(int type) {
        this.setType(type);
        return this;
    }

    public NewAccessModel withProtocol(String protocol) {
        this.setProtocol(protocol);
        return this;
    }

    public NewAccessModel withPort(int port) {
        this.setPort(port);
        return this;
    }

    public NewAccessModel withTargetPort(int targetPort) {
        this.setTargetPort(targetPort);
        return this;
    }

    public NewAccessModel withName(String name) {
        this.setName(name);
        return this;
    }


}
