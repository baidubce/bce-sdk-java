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
 * The model for hander.
 */
public class HandlerModel {
    /**
     * The exec action model.
     */
    private ExecActionModel exec;

    /**
     * The http get action model.
     */
    private HTTPGetActionModel httpGet;

    /**
     * The tcp socket action model.
     */
    private TCPSocketActionModel tcpSocket;

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

    public HandlerModel withExecActionModel(ExecActionModel exec) {
        this.setExec(exec);
        return this;
    }

    public HandlerModel withHTTPGetActionModel(HTTPGetActionModel httpGet) {
        this.setHttpGet(httpGet);
        return this;
    }

    public HandlerModel withTCPSocketAction(TCPSocketActionModel tcpSocket) {
        this.setTcpSocket(tcpSocket);
        return this;
    }
}
