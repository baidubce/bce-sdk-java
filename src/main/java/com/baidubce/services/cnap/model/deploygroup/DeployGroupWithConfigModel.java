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

import com.baidubce.services.cnap.model.application.ResourcesModel;

/**
 * The model for deploy group with config.
 */
public class DeployGroupWithConfigModel {

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
     * The replicas of deploy group.
     */
    private int replicas;

    /**
     * The id of environment.
     */
    private String environmentID;

    /**
     * The flag which indicates the deploy group is locked.
     */
    private boolean isLocked;

    /**
     * The release record id.
     */
    private String releaseRecordID;

    /**
     * The resource usage of deploy group.
     */
    private ResourcesModel resources;

    /**
     * The component conf of deploy group.
     */
    private List<NewComponentModel> componentConf;

    /**
     * The conf of deploy group.
     */
    private DeploySpecModel conf;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReplicas() {
        return replicas;
    }

    public void setReplicas(int replicas) {
        this.replicas = replicas;
    }

    public String getEnvironmentID() {
        return environmentID;
    }

    public void setEnvironmentID(String environmentID) {
        this.environmentID = environmentID;
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

    public ResourcesModel getResources() {
        return resources;
    }

    public void setResources(ResourcesModel resources) {
        this.resources = resources;
    }

    public List<NewComponentModel> getComponentConf() {
        return componentConf;
    }

    public void setComponentConf(List<NewComponentModel> componentConf) {
        this.componentConf = componentConf;
    }

    public DeploySpecModel getConf() {
        return conf;
    }

    public void setConf(DeploySpecModel conf) {
        this.conf = conf;
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
