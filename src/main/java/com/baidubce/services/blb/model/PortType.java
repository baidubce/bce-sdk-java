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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Port and type model for delete listener.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PortType {

    /**
     * the port of the listener.
     */
    private Integer port;

    /**
     * the type of the listener (TCP/UDP/HTTP/HTTPS/SSL).
     */
    private String type;

    public PortType() {
    }

    public PortType(Integer port, String type) {
        this.port = port;
        this.type = type;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PortType{" +
                "port=" + port +
                ", type='" + type + '\'' +
                '}';
    }
}