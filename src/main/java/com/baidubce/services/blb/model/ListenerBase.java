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

/**
 * The base listener info modal.
 */
public class ListenerBase {

    /**
     * the port of the listener.
     */
    private Integer listenerPort;
    /**
     * the backend port of the listener.
     */
    private Integer backendPort;
    /**
     * the scheduler of blb.
     */
    private String scheduler;
    /**
     * the timeout (in second) of health check.
     */
    private Integer healthCheckTimeoutInSecond;
    /**
     * the interval of health check.
     */
    private Integer healthCheckInterval;
    /**
     * the threshold of unhealthy.
     */
    private Integer unhealthyThreshold;
    /**
     * the threshold of healthy.
     */
    private Integer healthyThreshold;

    public Integer getListenerPort() {
        return listenerPort;
    }

    public void setListenerPort(Integer listenerPort) {
        this.listenerPort = listenerPort;
    }

    public Integer getBackendPort() {
        return backendPort;
    }

    public void setBackendPort(Integer backendPort) {
        this.backendPort = backendPort;
    }

    public String getScheduler() {
        return scheduler;
    }

    public void setScheduler(String scheduler) {
        this.scheduler = scheduler;
    }

    public Integer getHealthCheckTimeoutInSecond() {
        return healthCheckTimeoutInSecond;
    }

    public void setHealthCheckTimeoutInSecond(Integer healthCheckTimeoutInSecond) {
        this.healthCheckTimeoutInSecond = healthCheckTimeoutInSecond;
    }

    public Integer getHealthCheckInterval() {
        return healthCheckInterval;
    }

    public void setHealthCheckInterval(Integer healthCheckInterval) {
        this.healthCheckInterval = healthCheckInterval;
    }

    public Integer getUnhealthyThreshold() {
        return unhealthyThreshold;
    }

    public void setUnhealthyThreshold(Integer unhealthyThreshold) {
        this.unhealthyThreshold = unhealthyThreshold;
    }

    public Integer getHealthyThreshold() {
        return healthyThreshold;
    }

    public void setHealthyThreshold(Integer healthyThreshold) {
        this.healthyThreshold = healthyThreshold;
    }

}
