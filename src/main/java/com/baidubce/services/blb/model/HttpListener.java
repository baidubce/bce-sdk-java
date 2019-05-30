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
 * Http listener info modal.
 */
public class HttpListener extends ListenerBase {

    /**
     * if keep session or not.
     */
    private Boolean keepSession;
    /**
     * the type of keep session.
     */
    private String keepSessionType;
    /**
     * the duration of the keep session.
     */
    private Integer keepSessionDuration;
    /**
     * the cookie name of the keep session.
     */
    private String keepSessionCookieName;
    /**
     * if fetch the real ip or not.
     */
    private Boolean xForwardFor;
    /**
     * the type of the health check.
     */
    private String healthCheckType;
    /**
     * the port of the health check.
     */
    private Integer healthCheckPort;
    /**
     * the uri of the health check.
     */
    private String healthCheckURI;
    /**
     * the status of health check when it is normal.
     */
    private String healthCheckNormalStatus;
    /**
     * the max timeout of server.
     */
    private Integer serverTimeout;
    /**
     * the port of redirect.
     */
    private int redirectPort;

    public Boolean getKeepSession() {
        return keepSession;
    }

    public void setKeepSession(Boolean keepSession) {
        this.keepSession = keepSession;
    }

    public String getKeepSessionType() {
        return keepSessionType;
    }

    public void setKeepSessionType(String keepSessionType) {
        this.keepSessionType = keepSessionType;
    }

    public Integer getKeepSessionDuration() {
        return keepSessionDuration;
    }

    public void setKeepSessionDuration(Integer keepSessionDuration) {
        this.keepSessionDuration = keepSessionDuration;
    }

    public String getKeepSessionCookieName() {
        return keepSessionCookieName;
    }

    public void setKeepSessionCookieName(String keepSessionCookieName) {
        this.keepSessionCookieName = keepSessionCookieName;
    }

    public Boolean getxForwardFor() {
        return xForwardFor;
    }

    public void setxForwardFor(Boolean xForwardFor) {
        this.xForwardFor = xForwardFor;
    }

    public String getHealthCheckType() {
        return healthCheckType;
    }

    public void setHealthCheckType(String healthCheckType) {
        this.healthCheckType = healthCheckType;
    }

    public Integer getHealthCheckPort() {
        return healthCheckPort;
    }

    public void setHealthCheckPort(Integer healthCheckPort) {
        this.healthCheckPort = healthCheckPort;
    }

    public String getHealthCheckURI() {
        return healthCheckURI;
    }

    public void setHealthCheckURI(String healthCheckURI) {
        this.healthCheckURI = healthCheckURI;
    }

    public String getHealthCheckNormalStatus() {
        return healthCheckNormalStatus;
    }

    public void setHealthCheckNormalStatus(String healthCheckNormalStatus) {
        this.healthCheckNormalStatus = healthCheckNormalStatus;
    }

    public Integer getServerTimeout() {
        return serverTimeout;
    }

    public void setServerTimeout(Integer serverTimeout) {
        this.serverTimeout = serverTimeout;
    }

    public int getRedirectPort() {
        return redirectPort;
    }

    public void setRedirectPort(int redirectPort) {
        this.redirectPort = redirectPort;
    }
}
