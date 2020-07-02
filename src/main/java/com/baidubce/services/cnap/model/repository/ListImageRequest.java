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

package com.baidubce.services.cnap.model.repository;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for list images.
 */
public class ListImageRequest extends AbstractBceRequest {

    /**
     * The id of workspace.
     */
    private String workspaceID;

    /**
     * The id of repository.
     */
    private String repositoryID;

    /**
     * The order by info.
     */
    private String orderBy = "name";

    /**
     * The order info, eg asc, desc.
     */
    private String order = "asc";

    /**
     * The page size of list.
     */
    private int pageSize = 10;

    /**
     * The page no of list.
     */
    private int pageNo = 1;

    /**
     * The name of image.
     */
    private String name;

    public ListImageRequest withWorkspaceID(String workspaceID) {
        this.setWorkspaceID(workspaceID);
        return this;
    }

    public ListImageRequest withRepositoryID(String repositoryID) {
        this.setRepositoryID(repositoryID);
        return this;
    }

    public ListImageRequest withOrderBy(String orderBy) {
        this.setOrderBy(orderBy);
        return this;
    }

    public ListImageRequest withOrder(String order) {
        this.setOrder(order);
        return this;
    }

    public ListImageRequest withPageSize(int pageSize) {
        this.setPageSize(pageSize);
        return this;
    }

    public ListImageRequest withPageNo(int pageNo) {
        this.setPageNo(pageNo);
        return this;
    }

    public ListImageRequest withName(String name) {
        this.setName(name);
        return this;
    }

    public String getWorkspaceID() {
        return workspaceID;
    }

    public void setWorkspaceID(String workspaceID) {
        this.workspaceID = workspaceID;
    }

    public String getRepositoryID() {
        return repositoryID;
    }

    public void setRepositoryID(String repositoryID) {
        this.repositoryID = repositoryID;
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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public ListImageRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
