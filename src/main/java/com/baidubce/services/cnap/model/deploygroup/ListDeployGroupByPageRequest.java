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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for list deploy group by page.
 */
public class ListDeployGroupByPageRequest extends AbstractBceRequest {

    /**
     * The id of workspace.
     */
    private String workspaceID;

    /**
     * The id of application.
     */
    private String applicationID;


    /**
     * The orderBy info.
     */
    private String orderBy = "";

    /**
     * The order info, eg. asc, desc.
     */
    private String order = "";

    /**
     * The page size of list.
     */
    private String pageSize = "10";

    /**
     * The page no of list.
     */
    private String pageNo = "1";

    /**
     * The image info.
     */
    private String images;

    /**
     * The config id list.
     */
    private String configIDs;

    /**
     * The name of deploy group.
     */
    private String name;

    /**
     * The environment of deploy group.
     */
    private String environmentID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnvironmentID() {
        return environmentID;
    }

    public void setEnvironmentID(String environmentID) {
        this.environmentID = environmentID;
    }

    public String getWorkspaceID() {
        return workspaceID;
    }

    public void setWorkspaceID(String workspaceID) {
        this.workspaceID = workspaceID;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getConfigIDs() {
        return configIDs;
    }

    public void setConfigIDs(String configIDs) {
        this.configIDs = configIDs;
    }

    public ListDeployGroupByPageRequest withWorkspaceID(String workspaceID) {
        this.setWorkspaceID(workspaceID);
        return this;
    }

    public ListDeployGroupByPageRequest withApplicationID(String applicationID) {
        this.setApplicationID(applicationID);
        return this;
    }


    public ListDeployGroupByPageRequest withOrderBy(String orderBy) {
        this.setOrderBy(orderBy);
        return this;
    }

    public ListDeployGroupByPageRequest withOrder(String order) {
        this.setOrder(order);
        return this;
    }

    public ListDeployGroupByPageRequest withPageSize(String pageSize) {
        this.setPageSize(pageSize);
        return this;
    }

    public ListDeployGroupByPageRequest withPageNo(String pageNo) {
        this.setPageNo(pageNo);
        return this;
    }

    public ListDeployGroupByPageRequest withImages(String images) {
        this.setImages(images);
        return this;
    }

    public ListDeployGroupByPageRequest withConfigIDs(String configIDs) {
        this.setConfigIDs(configIDs);
        return this;
    }

    public ListDeployGroupByPageRequest withName(String name) {
        this.setName(name);
        return this;
    }

    public ListDeployGroupByPageRequest withEnvironmentID(String environmentID) {
        this.setEnvironmentID(environmentID);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public ListDeployGroupByPageRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
