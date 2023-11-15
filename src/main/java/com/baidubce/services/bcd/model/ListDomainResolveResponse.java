/*
 * Copyright (c) 2021 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcd.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;

import java.util.List;

/**
 * @author yangzhensheng
 * @date 2021/6/17
 * @desc the domain resolve record result list.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListDomainResolveResponse extends AbstractBceResponse {

    private String orderBy = "";

    private String order = "";

    private int pageNo = 1;

    private int pageSize = 0;

    private int totalCount = 0;

    private List<DomainRecord> result;

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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DomainRecord> getResult() {
        return result;
    }

    public void setResult(List<DomainRecord> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("orderBy", orderBy)
                .add("order", order)
                .add("pageNo", pageNo)
                .add("pageSize", pageSize)
                .add("totalCount", totalCount)
                .add("result", result)
                .toString();
    }
}
