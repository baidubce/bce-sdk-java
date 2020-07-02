/*
 * Copyright 2020 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bcc.model.snapshot;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * The response of getting the snap chain list.
 */
public class ListSnapchainResponse extends AbstractBceResponse {
    private String orderBy;
    private int totalCount;
    private int pageSize;
    private int pageNo;
    private boolean isTruncated;
    private List<SnapchainModel> snapchains;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
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

    public boolean isTruncated() {
        return isTruncated;
    }

    public void setTruncated(boolean truncated) {
        isTruncated = truncated;
    }

    public List<SnapchainModel> getSnapchains() {
        return snapchains;
    }

    public void setSnapchains(List<SnapchainModel> snapchains) {
        this.snapchains = snapchains;
    }

    @Override
    public String toString() {
        return "ListSnapchainResponse{" +
                "orderBy='" + orderBy + '\'' +
                ", totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", isTruncated=" + isTruncated +
                ", snapchains=" + snapchains +
                '}';
    }
}
