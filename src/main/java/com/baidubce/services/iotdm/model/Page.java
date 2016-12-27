/*
 * Copyright 2016 Baidu, Inc.
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
package com.baidubce.services.iotdm.model;

/**
 * Represent the page information in the device query request.
 */
public class Page {

    private String orderBy = "createTime";
    private String order = "desc";
    private int pageNo = 1;
    private int pageSize = 20;

    public String getOrderBy() {
        return orderBy;
    }

    /**
     * Set the orderby of the query result.
     * @param orderBy The orderby parameter: name|createTime|lastActiveTime, and 'createTime' by default.
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrder() {
        return order;
    }

    /**
     * Set the order of the query result.
     * @param order The order parameter: desc|asc, and 'desc' by default.
     */
    public void setOrder(String order) {
        this.order = order;
    }

    public int getPageNo() {
        return pageNo;
    }
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Page withOrderBy(String orderBy) {
        setOrderBy(orderBy);
        return this;
    }
    public Page withOrder(String order) {
        setOrder(order);
        return this;
    }
    public Page withPageNo(int pageNo) {
        setPageNo(pageNo);
        return this;
    }
    public Page withPageSize(int pageSize) {
        setPageSize(pageSize);
        return this;
    }

}
