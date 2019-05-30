/*
 * Copyright 2015 Baidu, Inc.
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

package com.baidubce.services.lss.model;

import java.io.Serializable;

public class Notification implements Serializable {
    /**
     * 通知名称
     **/
    private String name = null;

    /**
     * 通知消息接收地址
     **/
    private String endpoint = null;

    /**
     * 通知创建时间
     **/
    private String createTime = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Notification withName(String name) {
        this.name = name;
        return this;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Notification withEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Notification withCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class Notification {\n");
        sb.append("    name: ").append(name).append("\n");
        sb.append("    endpoint: ").append(endpoint).append("\n");
        sb.append("    createTime: ").append(createTime).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
