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

import java.util.List;

/**
 * All listener info modal.
 */
public class AllListener extends ListenerBase {

    /**
     * the type of listener.
     */
    private String listenerType;
    /**
     * if get blb ip or not.
     */
    private Boolean getBlbIp;
    /**
     * the timeout of the tcp session.
     */
    private Integer tcpSessionTimeout;
    /**
     * the timeout of the udp session.
     */
    private Integer udpSessionTimeout;
    /**
     * the string to health check.
     */
    private String healthCheckString;
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
     * the host of health check when it is http listener.
     */
    private String healthCheckHost;
    /**
     * the max timeout of server.
     */
    private Integer serverTimeout;
    /**
     * the port of redirect.
     */
    private int redirectPort;
    /**
     * the certificate ids of listener.
     */
    private List<String> certIds;
    /**
     * open dualAuth or not.
     */
    private boolean dualAuth;
    /**
     * the clientCert ids of listener.
     */
    private List<String> clientCertIds;
    /**
     * the encryptionType of listener.
     */
    private String encryptionType;
    /**
     * the encryptionProtocols of listener.
     */
    private List<String> encryptionProtocols;
    /**
     * the appliedCiphers of listener.
     */
    private String appliedCiphers;


    public String getListenerType() {
        return listenerType;
    }

    public void setListenerType(String listenerType) {
        this.listenerType = listenerType;
    }

    public Boolean getGetBlbIp() {
        return getBlbIp;
    }

    public void setGetBlbIp(Boolean getBlbIp) {
        this.getBlbIp = getBlbIp;
    }

    public Integer getTcpSessionTimeout() {
        return tcpSessionTimeout;
    }

    public void setTcpSessionTimeout(Integer tcpSessionTimeout) {
        this.tcpSessionTimeout = tcpSessionTimeout;
    }

    public Integer getUdpSessionTimeout() {
        return udpSessionTimeout;
    }

    public void setUdpSessionTimeout(Integer udpSessionTimeout) {
        this.udpSessionTimeout = udpSessionTimeout;
    }

    public String getHealthCheckString() {
        return healthCheckString;
    }

    public void setHealthCheckString(String healthCheckString) {
        this.healthCheckString = healthCheckString;
    }

    public String getHealthCheckHost() {
        return healthCheckHost;
    }

    public void setHealthCheckHost(String healthCheckHost) {
        this.healthCheckHost = healthCheckHost;
    }

    public List<String> getCertIds() {
        return certIds;
    }

    public void setCertIds(List<String> certIds) {
        this.certIds = certIds;
    }

    public boolean isDualAuth() {
        return dualAuth;
    }

    public void setDualAuth(boolean dualAuth) {
        this.dualAuth = dualAuth;
    }

    public List<String> getClientCertIds() {
        return clientCertIds;
    }

    public void setClientCertIds(List<String> clientCertIds) {
        this.clientCertIds = clientCertIds;
    }

    public String getEncryptionType() {
        return encryptionType;
    }

    public void setEncryptionType(String encryptionType) {
        this.encryptionType = encryptionType;
    }

    public List<String> getEncryptionProtocols() {
        return encryptionProtocols;
    }

    public void setEncryptionProtocols(List<String> encryptionProtocols) {
        this.encryptionProtocols = encryptionProtocols;
    }

    public String getAppliedCiphers() {
        return appliedCiphers;
    }

    public void setAppliedCiphers(String appliedCiphers) {
        this.appliedCiphers = appliedCiphers;
    }

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

    @Override
    public String toString() {
        return "AllListener{" +
                "listenerType='" + listenerType + '\'' +
                ", getBlbIp=" + getBlbIp +
                ", tcpSessionTimeout=" + tcpSessionTimeout +
                ", udpSessionTimeout=" + udpSessionTimeout +
                ", healthCheckString='" + healthCheckString + '\'' +
                ", keepSession=" + keepSession +
                ", keepSessionType='" + keepSessionType + '\'' +
                ", keepSessionDuration=" + keepSessionDuration +
                ", keepSessionCookieName='" + keepSessionCookieName + '\'' +
                ", xForwardFor=" + xForwardFor +
                ", healthCheckType='" + healthCheckType + '\'' +
                ", healthCheckPort=" + healthCheckPort +
                ", healthCheckURI='" + healthCheckURI + '\'' +
                ", healthCheckNormalStatus='" + healthCheckNormalStatus + '\'' +
                ", healthCheckHost='" + healthCheckHost + '\'' +
                ", serverTimeout=" + serverTimeout +
                ", redirectPort=" + redirectPort +
                ", certIds=" + certIds +
                ", dualAuth=" + dualAuth +
                ", clientCertIds=" + clientCertIds +
                ", encryptionType='" + encryptionType + '\'' +
                ", encryptionProtocols=" + encryptionProtocols +
                ", appliedCiphers='" + appliedCiphers + '\'' +
                '}';
    }
}
