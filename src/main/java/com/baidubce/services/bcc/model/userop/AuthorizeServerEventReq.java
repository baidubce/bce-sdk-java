/*
 * Copyright 2025 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model.userop;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class AuthorizeServerEventReq extends AbstractBceRequest {
    /**
     * event Id
     */
    private String serverEventId;

    /**
     * Instance ID, server Event ID, instance ID must select one of the request options
     */
    private String instanceId;

    /**
     * Authorized operation and maintenance operations
     */
    private String authorizeMaintenanceOperation;

    /**
     * Authorized execution time
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Date executeTime;

    public void setServerEventId(String serverEventId) {
        this.serverEventId = serverEventId;
    }

    public String getServerEventId() {
        return this.serverEventId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceId() {
        return this.instanceId;
    }

    public void setAuthorizeMaintenanceOperation(String authorizeMaintenanceOperation) {
        this.authorizeMaintenanceOperation = authorizeMaintenanceOperation;
    }

    public String getAuthorizeMaintenanceOperation() {
        return this.authorizeMaintenanceOperation;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public Date getExecuteTime() {
        return this.executeTime;
    }

    @Override
    public String toString() {
        return "AuthorizeServerEventReq{"
                + "serverEventId=" + serverEventId + "\n"
                + "instanceId=" + instanceId + "\n"
                + "authorizeMaintenanceOperation=" + authorizeMaintenanceOperation + "\n"
                + "executeTime=" + executeTime + "\n"
                + "}";
    }

    @Override
    public AuthorizeServerEventReq withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}