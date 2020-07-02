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

/**
 * The model of deploy group.
 */
public class DeployGroupModel {
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
     * The deploy strategy type of deploy group.
     */
    private String deployStrategyType;

    /**
     * The replicas of deploy group;
     */
    private int replicas;

    /**
     * The type of deploy group.
     */
    private int type;

    /**
     * The environment id of deploy group.
     */
    private String environmentID;

    /**
     * Deploy group is locked.
     */
    private boolean isLocked;

    /**
     * The release record id of deploy group.
     */
    private String releaseRecordID;

    /**
     * The status of deploy group.
     */
    private String status;

    /**
     * The create time of deploy group.
     */
    private Date createdAt;

    /**
     * The update time of deploy group.
     */
    private Date updatedAt;

    /**
     * The cluster info of deploy group.
     */
    private ClusterModel cluster;

    /**
     * The environment info of deploy group.
     */
    private EnvironmentModel environment;

    /**
     * The resources of deploy group.
     */
    private ResourcesModel resources;

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

    public String getDeployStrategyType() {
        return deployStrategyType;
    }

    public void setDeployStrategyType(String deployStrategyType) {
        this.deployStrategyType = deployStrategyType;
    }

    public int getReplicas() {
        return replicas;
    }

    public void setReplicas(int replicas) {
        this.replicas = replicas;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
        return isLocked;
    }

    public String getReleaseRecordID() {
        return releaseRecordID;
    }

    public void setReleaseRecordID(String releaseRecordID) {
        this.releaseRecordID = releaseRecordID;
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

    public ClusterModel getCluster() {
        return cluster;
    }

    public void setCluster(ClusterModel cluster) {
        this.cluster = cluster;
    }

    public EnvironmentModel getEnvironment() {
        return environment;
    }

    public void setEnvironment(EnvironmentModel environment) {
        this.environment = environment;
    }

    public ResourcesModel getResources() {
        return resources;
    }

    public void setResources(ResourcesModel resources) {
        this.resources = resources;
    }
}
