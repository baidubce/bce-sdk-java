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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for list application.
 */
public class ListApplicationRequest extends AbstractBceRequest {

    /**
     * The id of workspace.
     */
    private String workspaceID;

    /**
     * The orderBy info, eg. createdAt(workspace create time)
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
     * The name of application.
     */
    private String name;

    /**
     * The id of application.
     */
    private String resourceID;

    /**
     * The type of application deploy.
     * 1 indicates k8s deploy.
     */
    private String deployType;

    /**
     * The type of application.
     * 1 indicates common application.
     * 4 indicates micro service application.
     */
    private int workloadType;

    public String getWorkspaceID() {
        return workspaceID;
    }

    public void setWorkspaceID(String workspaceID) {
        this.workspaceID = workspaceID;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public String getDeployType() {
        return deployType;
    }

    public void setDeployType(String deployType) {
        this.deployType = deployType;
    }

    public int getWorkloadType() {
        return workloadType;
    }

    public void setWorkloadType(int workloadType) {
        this.workloadType = workloadType;
    }

    public ListApplicationRequest withWorkspaceID(String workspaceID) {
        this.setWorkspaceID(workspaceID);
        return this;
    }

    public ListApplicationRequest withOrderBy(String orderBy) {
        this.setOrderBy(orderBy);
        return this;
    }

    public ListApplicationRequest withOrder(String order) {
        this.setOrder(order);
        return this;
    }

    public ListApplicationRequest withPageSize(String pageSize) {
        this.setPageSize(pageSize);
        return this;
    }

    public ListApplicationRequest withPageNo(String pageNo) {
        this.setPageNo(pageNo);
        return this;
    }

    public ListApplicationRequest withName(String name) {
        this.setName(name);
        return this;
    }

    public ListApplicationRequest withResourceID(String resourceID) {
        this.setResourceID(resourceID);
        return this;
    }

    public ListApplicationRequest withDeployType(String deployType) {
        this.setDeployType(deployType);
        return this;
    }

    public ListApplicationRequest withWorkloadType(int workloadType) {
        this.setWorkloadType(workloadType);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public ListApplicationRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
