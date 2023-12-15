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
 * The model of appBlb backendServer port.
 */
public class AppRsPort {

    /**
     * the listener port of rs.
     */
    private Integer listenerPort;
    /**
     * the port of rs.
     */
    private String backendPort;
    /**
     * the type of port.
     */
    private String portType;
    /**
     * the protocol of health check.
     */
    private String healthCheckPortType;
    /**
     * the status of port.
     */
    private String status;
    /**
     * the id of port.
     */
    private String portId;
    /**
     * the id of policy.
     */
    private String policyId;

    public Integer getListenerPort() {
        return listenerPort;
    }

    public void setListenerPort(Integer listenerPort) {
        this.listenerPort = listenerPort;
    }

    public String getBackendPort() {
        return backendPort;
    }

    public void setBackendPort(String backendPort) {
        this.backendPort = backendPort;
    }

    public String getPortType() {
        return portType;
    }

    public void setPortType(String portType) {
        this.portType = portType;
    }

    public String getHealthCheckPortType() {
        return healthCheckPortType;
    }

    public void setHealthCheckPortType(String healthCheckPortType) {
        this.healthCheckPortType = healthCheckPortType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPortId() {
        return portId;
    }

    public void setPortId(String portId) {
        this.portId = portId;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    @Override
    public String toString() {
        return "AppRsPort{" +
                "listenerPort=" + listenerPort +
                ", backendPort='" + backendPort + '\'' +
                ", portType='" + portType + '\'' +
                ", healthCheckPortType='" + healthCheckPortType + '\'' +
                ", status='" + status + '\'' +
                ", portId='" + portId + '\'' +
                ", policyId='" + policyId + '\'' +
                '}';
    }
}
