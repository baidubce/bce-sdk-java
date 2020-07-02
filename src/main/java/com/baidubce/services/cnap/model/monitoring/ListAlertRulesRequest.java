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
package com.baidubce.services.cnap.model.monitoring;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for list alert rules.
 */
public class ListAlertRulesRequest extends AbstractBceRequest {

    /**
     * The order by info.
     */
    private String orderBy;

    /**
     * The order info, eg asc, desc.
     */
    private String order;

    /**
     * The page size of list.
     */
    private int pageSize = 10;

    /**
     * The page no of list.
     */
    private int pageNo = 1;

    /**
     * The name of alert rule.
     */
    private String name;

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

    public ListAlertRulesRequest withOrderBy(String orderBy) {
        this.setOrderBy(orderBy);
        return this;
    }

    public ListAlertRulesRequest withOrder(String order) {
        this.setOrder(order);
        return this;
    }

    public ListAlertRulesRequest withPageSize(int pageSize) {
        this.setPageSize(pageSize);
        return this;
    }

    public ListAlertRulesRequest withPageNo(int pageNo) {
        this.setPageNo(pageNo);
        return this;
    }

    public ListAlertRulesRequest withName(String name) {
        this.setName(name);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public ListAlertRulesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
