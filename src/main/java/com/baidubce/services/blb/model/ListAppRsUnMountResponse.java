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

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * The response for list appBlb backendServer
 */
public class ListAppRsUnMountResponse extends AbstractBceResponse {
    /**
     * the backendServer info list.
     */
    private List<AppUnMountBackendServer> backendServerList;

    public List<AppUnMountBackendServer> getBackendServerList() {
        return backendServerList;
    }

    public void setBackendServerList(List<AppUnMountBackendServer> backendServerList) {
        this.backendServerList = backendServerList;
    }

    @Override
    public String toString() {
        return "ListAppRsUnMountResponse{" +
                "backendServerList=" + backendServerList +
                '}';
    }
}
