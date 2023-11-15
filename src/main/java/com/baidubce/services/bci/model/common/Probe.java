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
 * The probe of container
 */
public class Probe {

    /**
     * The exec action of probe
     */
    private ExecAction exec;

    /**
     * The http get action of probe
     */
    private HTTPGetAction httpGet;

    /**
     * The tcp socket action of probe
     */
    private TCPSocketAction tcpSocket;

    /**
     * The grpc action of probe
     */
    private GRPCAction grpc;

    /**
     * The initial delay seconds of probe
     */
    private int initialDelaySeconds;

    /**
     * The timeout seconds of probe
     */
    private int timeoutSeconds;

    /**
     * The period seconds of probe
     */
    private int periodSeconds;

    /**
     * The success threshold of probe
     */
    private int successThreshold;

    /**
     * The failure threshold of probe
     */
    private int failureThreshold;

    /**
     * The termination grace period seconds of probe
     */
    private long terminationGracePeriodSeconds;

    /**
     * The constructor of Probe
     */
    public Probe() {

    }

    /**
     * The constructor of Probe
     * @param exec The exec action of probe
     * @param httpGet The http get action of probe
     * @param tcpSocket The tcp socket action of probe
     * @param grpc The grpc action of probe
     * @param initialDelaySeconds The initial delay seconds of probe
     * @param timeoutSeconds The timeout seconds of probe
     * @param periodSeconds The period seconds of probe
     * @param successThreshold The success threshold of probe
     * @param failureThreshold The failure threshold of probe
     * @param terminationGracePeriodSeconds The termination grace period seconds of probe
     */
    public Probe(ExecAction exec, HTTPGetAction httpGet, TCPSocketAction tcpSocket, GRPCAction grpc,
                 int initialDelaySeconds, int timeoutSeconds, int periodSeconds, int successThreshold,
                 int failureThreshold, long terminationGracePeriodSeconds) {
        this.exec = exec;
        this.httpGet = httpGet;
        this.tcpSocket = tcpSocket;
        this.grpc = grpc;
        this.initialDelaySeconds = initialDelaySeconds;
        this.timeoutSeconds = timeoutSeconds;
        this.periodSeconds = periodSeconds;
        this.successThreshold = successThreshold;
        this.failureThreshold = failureThreshold;
        this.terminationGracePeriodSeconds = terminationGracePeriodSeconds;
    }

    public ExecAction getExec() {
        return exec;
    }

    public Probe setExec(ExecAction exec) {
        this.exec = exec;
        return this;
    }

    public HTTPGetAction getHttpGet() {
        return httpGet;
    }

    public Probe setHttpGet(HTTPGetAction httpGet) {
        this.httpGet = httpGet;
        return this;
    }

    public TCPSocketAction getTcpSocket() {
        return tcpSocket;
    }

    public Probe setTcpSocket(TCPSocketAction tcpSocket) {
        this.tcpSocket = tcpSocket;
        return this;
    }

    public GRPCAction getGrpc() {
        return grpc;
    }

    public Probe setGrpc(GRPCAction grpc) {
        this.grpc = grpc;
        return this;
    }

    public int getInitialDelaySeconds() {
        return initialDelaySeconds;
    }

    public Probe setInitialDelaySeconds(int initialDelaySeconds) {
        this.initialDelaySeconds = initialDelaySeconds;
        return this;
    }

    public int getTimeoutSeconds() {
        return timeoutSeconds;
    }

    public Probe setTimeoutSeconds(int timeoutSeconds) {
        this.timeoutSeconds = timeoutSeconds;
        return this;
    }

    public int getPeriodSeconds() {
        return periodSeconds;
    }

    public Probe setPeriodSeconds(int periodSeconds) {
        this.periodSeconds = periodSeconds;
        return this;
    }

    public int getSuccessThreshold() {
        return successThreshold;
    }

    public Probe setSuccessThreshold(int successThreshold) {
        this.successThreshold = successThreshold;
        return this;
    }

    public int getFailureThreshold() {
        return failureThreshold;
    }

    public Probe setFailureThreshold(int failureThreshold) {
        this.failureThreshold = failureThreshold;
        return this;
    }

    public long getTerminationGracePeriodSeconds() {
        return terminationGracePeriodSeconds;
    }

    public Probe setTerminationGracePeriodSeconds(long terminationGracePeriodSeconds) {
        this.terminationGracePeriodSeconds = terminationGracePeriodSeconds;
        return this;
    }
}