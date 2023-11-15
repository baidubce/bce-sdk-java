/*
 * Copyright (c) 2023 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.bci.model.common;

/**
 * The port of container
 */
public class Port {

    /**
     * The port of protocol
     */
    private int port;

    /**
     * The protocol of port
     */
    private String protocol;

    /**
     * The name of port
     */
    private String name;

    /**
     * The constructor of Port
     */
    public Port() {

    }

    /**
     * The constructor of Port
     * @param port The port of protocol
     * @param protocol The protocol of port
     * @param name The name of port
     */
    public Port(int port, String protocol, String name) {
        this.port = port;
        this.protocol = protocol;
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public Port setPort(int port) {
        this.port = port;
        return this;
    }

    public String getProtocol() {
        return protocol;
    }

    public Port setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public String getName() {
        return name;
    }

    public Port setName(String name) {
        this.name = name;
        return this;
    }
}