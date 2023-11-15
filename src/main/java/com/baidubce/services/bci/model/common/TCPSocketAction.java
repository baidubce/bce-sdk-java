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
 * The tcp socket action of container
 */
public class TCPSocketAction {

    /**
     * The port of tcp socket
     */
    private int port;

    /**
     * The host of tcp socket
     */
    private String host;

    /**
     * The constructor of TCPSocketAction
     */
    public TCPSocketAction() {

    }

    /**
     * The constructor of TCPSocketAction
     * @param port The port of tcp socket
     * @param host The host of tcp socket
     */
    public TCPSocketAction(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public TCPSocketAction setPort(int port) {
        this.port = port;
        return this;
    }

    public String getHost() {
        return host;
    }

    public TCPSocketAction setHost(String host) {
        this.host = host;
        return this;
    }
}