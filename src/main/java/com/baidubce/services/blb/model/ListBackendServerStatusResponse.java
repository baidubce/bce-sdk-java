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

import com.baidubce.model.ListResponse;

/**
 * The response for list backendServer status.
 */
public class ListBackendServerStatusResponse extends ListResponse {

    /**
     * the backendServer info list.
     */
    private List<BackendServerStatus> backendServerList;
    /**
     * the type of the backendServer.
     */
    private String type;
    /**
     * the port of the listener.
     */
    private int listenerPort;
    /**
     * the port of the backend.
     */
    private int backendPort;

    public List<BackendServerStatus> getBackendServerList() {
        return backendServerList;
    }

    public void setBackendServerList(List<BackendServerStatus> backendServerList) {
        this.backendServerList = backendServerList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getListenerPort() {
        return listenerPort;
    }

    public void setListenerPort(int listenerPort) {
        this.listenerPort = listenerPort;
    }

    public int getBackendPort() {
        return backendPort;
    }

    public void setBackendPort(int backendPort) {
        this.backendPort = backendPort;
    }

    @Override
    public String toString() {
        return "ListBackendServerStatusResponse{" +
                "marker=" + getMarker() + "," +
                "maxKeys=" + getMaxKeys() + "," +
                "isTruncated=" + getIsTruncated() + "," +
                "nextMarker=" + getNextMarker() + "," +
                "backendServerList=" + backendServerList +
                ", type='" + type + '\'' +
                ", listenerPort=" + listenerPort +
                ", backendPort=" + backendPort +
                '}';
    }
}
