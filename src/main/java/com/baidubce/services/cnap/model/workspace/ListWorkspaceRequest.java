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

package com.baidubce.services.cnap.model.workspace;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for list workspace.
 */
public class ListWorkspaceRequest extends AbstractBceRequest {

    /**
     * The orderBy info, eg. createdAt(workspace create time)
     */
    private String orderBy = "createdAt";

    /**
     * The order info, eg. asc, desc.
     */
    private String order = "desc";

    /**
     * The page size of list.
     */
    private String pageSize = "10";

    /**
     * The page no of list.
     */
    private String pageNo = "1";

    /**
     * The name of workspace.
     */
    private String name;

    /**
     * The id of workspace.
     */
    private String resourceID;

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

    public ListWorkspaceRequest withOrderBy(String orderBy) {
        this.setOrderBy(orderBy);
        return this;
    }

    public ListWorkspaceRequest withOrder(String order) {
        this.setOrder(order);
        return this;
    }

    public ListWorkspaceRequest withPageSize(String pageSize) {
        this.setPageSize(pageSize);
        return this;
    }

    public ListWorkspaceRequest withPageNo(String pageNo) {
        this.setPageNo(pageNo);
        return this;
    }

    public ListWorkspaceRequest withName(String name) {
        this.setName(name);
        return this;
    }

    public ListWorkspaceRequest withResourceID(String resourceID) {
        this.setResourceID(resourceID);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public ListWorkspaceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
