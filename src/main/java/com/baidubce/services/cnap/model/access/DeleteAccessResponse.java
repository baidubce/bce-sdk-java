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

package com.baidubce.services.cnap.model.access;

import java.util.Date;

import com.baidubce.model.AbstractBceResponse;

/**
 * The response for delete access.
 */
public class DeleteAccessResponse extends AbstractBceResponse {

    /**
     * The id of access.
     */
    private String resourceID;

    /**
     * The name of access.
     */
    private String name;

    /**
     * The type of access.
     * 1 indicate access in cluster network.
     * 2 indicate access in vpc network.
     * 3 indicate access in public network.
     */
    private int type;

    /**
     * The protocol of access.
     */
    private String protocol;


    /**
     * The port of access.
     */
    private int port;

    /**
     * The port of listening.
     */
    private int targetPort;

    /**
     * The inner endpoint.
     */
    private String innerEndpoint;

    /**
     * The ip of access.
     */
    private String ip;

    /**
     * The status of access.
     */
    private String status;

    /**
     * The create time.
     */
    private Date createdAt;

    /**
     * The update time.
     */
    private Date updatedAt;

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTargetPort() {
        return targetPort;
    }

    public void setTargetPort(int targetPort) {
        this.targetPort = targetPort;
    }

    public String getInnerEndpoint() {
        return innerEndpoint;
    }

    public void setInnerEndpoint(String innerEndpoint) {
        this.innerEndpoint = innerEndpoint;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
