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

package com.baidubce.services.cnap.model.deploygroup;

/**
 * The model for probe.
 */
public class ProbeModel {

    /**
     * The exec action command.
     */
    private ExecActionModel exec;

    /**
     * The http action.
     */
    private HTTPGetActionModel httpGet;

    /**
     * The tcp socket.
     */
    private TCPSocketActionModel tcpSocket;

    /**
     * The time out.
     */
    private int timeoutSeconds = 1;

    /**
     * The period time to start probe.
     */
    private int periodSeconds;

    public ExecActionModel getExec() {
        return exec;
    }

    public void setExec(ExecActionModel exec) {
        this.exec = exec;
    }

    public HTTPGetActionModel getHttpGet() {
        return httpGet;
    }

    public void setHttpGet(HTTPGetActionModel httpGet) {
        this.httpGet = httpGet;
    }

    public TCPSocketActionModel getTcpSocket() {
        return tcpSocket;
    }

    public void setTcpSocket(TCPSocketActionModel tcpSocket) {
        this.tcpSocket = tcpSocket;
    }

    public int getTimeoutSeconds() {
        return timeoutSeconds;
    }

    public void setTimeoutSeconds(int timeoutSeconds) {
        this.timeoutSeconds = timeoutSeconds;
    }

    public int getPeriodSeconds() {
        return periodSeconds;
    }

    public void setPeriodSeconds(int periodSeconds) {
        this.periodSeconds = periodSeconds;
    }


}
