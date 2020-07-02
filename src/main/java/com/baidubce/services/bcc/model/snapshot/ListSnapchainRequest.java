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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for getting the snap chain list;
 */
public class ListSnapchainRequest extends AbstractBceRequest {

    /**
     * Sorting properties, optional: chainId (snapshot chain id, default),
     * chainSize (snapshot chain size), volumeSize (disk size)
     */
    private String orderBy = "chainId";

    /**
     * Sorting method Optional: asc (positive order, default), desc (reverse order)
     */
    private String order = "asc";

    /**
     * The number of each page, the default value is 1000.
     */
    private int pageSize = 1000;

    private int pageNo = 1;

    /**
     * Disk id, if this field is not empty,
     * only the snapshot chain information of this disk will be returned
     */
    private String volumeId;

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

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    @Override
    public String toString() {
        return "ListSnapchainRequest{" +
                "orderBy='" + orderBy + '\'' +
                ", order='" + order + '\'' +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", volumeId='" + volumeId + '\'' +
                '}';
    }

    @Override
    public ListSnapchainRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
