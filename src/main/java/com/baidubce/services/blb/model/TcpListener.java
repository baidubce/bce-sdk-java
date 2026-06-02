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
 * Tcp listener info modal.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TcpListener extends ListenerBase {

    /**
     * tcp session timeout (seconds), default 900, range 10-4000
     */
    private Integer tcpSessionTimeout;

    /**
     * health check type, default "TCP"
     */
    private String healthCheckType;

    public Integer getTcpSessionTimeout() {
        return tcpSessionTimeout;
    }

    public void setTcpSessionTimeout(Integer tcpSessionTimeout) {
        this.tcpSessionTimeout = tcpSessionTimeout;
    }

    public String getHealthCheckType() {
        return healthCheckType;
    }

    public void setHealthCheckType(String healthCheckType) {
        this.healthCheckType = healthCheckType;
    }

    @Override
    public String toString() {
        return "TcpListener{" +
                "tcpSessionTimeout=" + tcpSessionTimeout +
                ", healthCheckType='" + healthCheckType + '\'' +
                "} " + super.toString();
    }
}
