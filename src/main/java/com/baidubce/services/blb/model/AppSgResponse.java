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
 * The response for appBlb serverGroup.
 */
public class AppSgResponse extends AbstractBceResponse {

    /**
     * the short id of the AppServerGroup.
     */
    private String id;
    /**
     * the name of AppServerGroup.
     */
    private String name;
    /**
     * the description of AppServerGroup.
     */
    private String desc;
    /**
     * the status of AppServerGroup.
     */
    private String status;
    /**
     * the portList of AppServerGroup.
     */
    private List<AppSgPortResponse> portList;

    public List<AppSgPortResponse> getPortList() {
        return portList;
    }

    public void setPortList(List<AppSgPortResponse> portList) {
        this.portList = portList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
