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
 * Ddp listener info modal.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UdpListener extends ListenerBase {

    /**
     * health check type, supports "UDP"/"ICMP"
     */
    private String healthCheckType;

    /**
     * the string to health check.
     */
    private String healthCheckString;

    /**
     * health check port, when health check type is "UDP"
     */
    private Integer healthCheckPort;

    /**
     * udp session timeout (seconds), default 90, range 5-4000
     */
    private Integer udpSessionTimeout;

    public String getHealthCheckType() {
        return healthCheckType;
    }

    public void setHealthCheckType(String healthCheckType) {
        this.healthCheckType = healthCheckType;
    }

    public String getHealthCheckString() {
        return healthCheckString;
    }

    public void setHealthCheckString(String healthCheckString) {
        this.healthCheckString = healthCheckString;
    }

    public Integer getHealthCheckPort() {
        return healthCheckPort;
    }

    public void setHealthCheckPort(Integer healthCheckPort) {
        this.healthCheckPort = healthCheckPort;
    }

    public Integer getUdpSessionTimeout() {
        return udpSessionTimeout;
    }

    public void setUdpSessionTimeout(Integer udpSessionTimeout) {
        this.udpSessionTimeout = udpSessionTimeout;
    }

    @Override
    public String toString() {
        return "UdpListener{" +
                "healthCheckType='" + healthCheckType + '\'' +
                ", healthCheckString='" + healthCheckString + '\'' +
                ", healthCheckPort=" + healthCheckPort +
                ", udpSessionTimeout=" + udpSessionTimeout +
                "} " + super.toString();
    }
}
