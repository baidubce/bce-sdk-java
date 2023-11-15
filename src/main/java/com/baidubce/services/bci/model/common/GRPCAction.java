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
 * The grpc action of container
 */
public class GRPCAction {
    /**
     * The port of grpc service
     */
    private int port;
    /**
     * The name of grpc service
     */
    private String service;

    /**
     * The constructor of GRPCAction
     */
    public GRPCAction() {

    }

    /**
     * The constructor of GRPCAction
     * @param port The port of grpc service
     * @param service The name of grpc service
     */
    public GRPCAction(int port, String service) {
        this.port = port;
        this.service = service;
    }

    public int getPort() {
        return port;
    }

    public GRPCAction setPort(int port) {
        this.port = port;
        return this;
    }

    public String getService() {
        return service;
    }

    public GRPCAction setService(String service) {
        this.service = service;
        return this;
    }
}