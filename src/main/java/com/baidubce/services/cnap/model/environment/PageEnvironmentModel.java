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

package com.baidubce.services.cnap.model.environment;

import java.util.Date;
import java.util.List;

import com.baidubce.services.cnap.model.application.PageApplicationModel;
import com.baidubce.services.cnap.model.cluster.ClusterModel;

/**
 * The model for page environment.
 */
public class PageEnvironmentModel {

    /**
     * The id of environment.
     */
    private String resourceID;

    /**
     * The name of environment.
     */
    private String name;

    /**
     * The description of environment.
     */
    private String description;

    /**
     * The cluster id.
     */
    private String clusterID;

    /**
     * The create time.
     */
    private Date createdAt;

    /**
     * The update time.
     */
    private Date updatedAt;

    /**
     * The application list in environment.
     */
    private List<PageApplicationModel> applicationList;

    /**
     * The cluster info.
     */
    private ClusterModel cluster;

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

    public String getClusterID() {
        return clusterID;
    }

    public void setClusterID(String clusterID) {
        this.clusterID = clusterID;
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

    public List<PageApplicationModel> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(
            List<PageApplicationModel> applicationList) {
        this.applicationList = applicationList;
    }

    public ClusterModel getCluster() {
        return cluster;
    }

    public void setCluster(ClusterModel cluster) {
        this.cluster = cluster;
    }
}
