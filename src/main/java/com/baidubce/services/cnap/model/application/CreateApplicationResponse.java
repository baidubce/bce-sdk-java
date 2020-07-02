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

package com.baidubce.services.cnap.model.application;

import java.util.Date;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.cnap.model.workspace.WorkspaceBaseInfoModel;

/**
 * The response for create application.
 */
public class CreateApplicationResponse extends AbstractBceResponse {
    /**
     * The id of application.
     */
    private String resourceID;

    /**
     * The name of application.
     */
    private String name;

    /**
     * The description of application.
     */
    private String description;

    /**
     * The deploy type of application.
     */
    private int deployType;

    /**
     * The type of application.
     */
    private int workloadType;

    /**
     * The id of workspace.
     */
    private String workspaceID;

    /**
     * The create time.
     */
    private Date createdAt;

    /**
     * The update time.
     */
    private Date updatedAt;

    /**
     * The workspace base info.
     */
    private WorkspaceBaseInfoModel workspace;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDeployType() {
        return deployType;
    }

    public void setDeployType(int deployType) {
        this.deployType = deployType;
    }

    public int getWorkloadType() {
        return workloadType;
    }

    public void setWorkloadType(int workloadType) {
        this.workloadType = workloadType;
    }

    public String getWorkspaceID() {
        return workspaceID;
    }

    public void setWorkspaceID(String workspaceID) {
        this.workspaceID = workspaceID;
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

    public WorkspaceBaseInfoModel getWorkspace() {
        return workspace;
    }

    public void setWorkspace(WorkspaceBaseInfoModel workspace) {
        this.workspace = workspace;
    }
}

