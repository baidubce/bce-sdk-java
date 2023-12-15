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
package com.baidubce.services.blb.model;



/**
 * The request for list appBlb unMount backendServer.
 */
public class AppUnMountBackendServer {

    /**
     * the instanceId of the backendServer.
     */
    private String instanceId;
    /**
     * the privateIp of the backendServer.
     */
    private String privateIp;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getPrivateIp() {
        return privateIp;
    }

    public void setPrivateIp(String privateIp) {
        this.privateIp = privateIp;
    }

    @Override
    public String toString() {
        return "AppUnMountBackendServer{" +
                "instanceId='" + instanceId + '\'' +
                ", privateIp='" + privateIp + '\'' +
                '}';
    }
}
