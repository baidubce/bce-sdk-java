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

import java.util.Date;
import java.util.List;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.cnap.model.application.ApplicationBaseInfoModel;
import com.baidubce.services.cnap.model.application.EnvironmentModel;
import com.baidubce.services.cnap.model.application.ResourcesModel;
import com.baidubce.services.cnap.model.workspace.WorkspaceBaseInfoModel;

/**
 * The response for get deploy group.
 */
public class GetDeployGroupResponse extends AbstractBceResponse {
    /**
     * The id of deploy group.
     */
    private String resourceID;

    /**
     * The name of deploy group.
     */
    private String name;

    /**
     * The description of deploy group.
     */
    private String description;

    /**
     * The id of environment.
     */
    private String environmentID;

    /**
     * The count of replicas.
     */
    private int replicas;

    /**
     * The flag which indicates deploy group is locked.
     */
    private boolean isLocked;

    /**
     * The type of deploy group.
     */
    private int type;

    /**
     * The id of release record.
     */
    private String releaseRecordID;

    /**
     * The create time.
     */
    private Date createdAt;

    /**
     * The update time.
     */
    private Date updatedAt;

    /**
     * The resource info.
     */
    private ResourcesModel resources;

    /**
     * The type of deploy strategy.
     */
    private String deployStrategyType;

    /**
     * The deploy group conf.
     */
    private DeploySpecModel conf;

    /**
     * The component conf.
     */
    private List<ComponentModel> componentConf;

    /**
     * The workspace info.
     */
    private WorkspaceBaseInfoModel workspace;

    /**
     * The application info.
     */
    private ApplicationBaseInfoModel application;

    /**
     * The environment info.
     */
    private EnvironmentModel environment;

    /**
     * The status of deploy group.
     */
    private String status;


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

    public String getEnvironmentID() {
        return environmentID;
    }

    public void setEnvironmentID(String environmentID) {
        this.environmentID = environmentID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getReplicas() {
        return replicas;
    }

    public void setReplicas(int replicas) {
        this.replicas = replicas;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public boolean getIsLocked() {
        return this.isLocked;

    }
    public String getReleaseRecordID() {
        return releaseRecordID;
    }

    public void setReleaseRecordID(String releaseRecordID) {
        this.releaseRecordID = releaseRecordID;
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

    public ResourcesModel getResources() {
        return resources;
    }

    public void setResources(ResourcesModel resources) {
        this.resources = resources;
    }

    public String getDeployStrategyType() {
        return deployStrategyType;
    }

    public void setDeployStrategyType(String deployStrategyType) {
        this.deployStrategyType = deployStrategyType;
    }

    public DeploySpecModel getConf() {
        return conf;
    }

    public void setConf(DeploySpecModel conf) {
        this.conf = conf;
    }

    public List<ComponentModel> getComponentConf() {
        return componentConf;
    }

    public void setComponentConf(List<ComponentModel> componentConf) {
        this.componentConf = componentConf;
    }

    public WorkspaceBaseInfoModel getWorkspace() {
        return workspace;
    }

    public void setWorkspace(WorkspaceBaseInfoModel workspace) {
        this.workspace = workspace;
    }

    public ApplicationBaseInfoModel getApplication() {
        return application;
    }

    public void setApplication(ApplicationBaseInfoModel application) {
        this.application = application;
    }

    public EnvironmentModel getEnvironment() {
        return environment;
    }

    public void setEnvironment(EnvironmentModel environment) {
        this.environment = environment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
