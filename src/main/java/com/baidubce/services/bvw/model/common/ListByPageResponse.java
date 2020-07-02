/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.bvw.model.common;

import java.util.Collection;
import java.util.LinkedList;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bvw.BvwResponseMetadata;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * List By Page Response, contains page content, page number,
 * page size and total count.
 * @param <T> The page content model.
 */
public class ListByPageResponse<T> extends AbstractBceResponse {

    /**
     * The list page content.
     */
    private Collection<T> data = new LinkedList<T>();
    /**
     * The page number of list by page request.
     */
    private int pageNo = 1;
    /**
     * The number of element in each page.
     */
    private int pageSize = 0;
    /**
     * The total count.
     */
    private long totalCount = 0L;

    public ListByPageResponse() {
        this.metadata = new BvwResponseMetadata();
    }

    public Collection<T> getData() {
        return data;
    }

    public void setData(Collection<T> data) {
        this.data = data;
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

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("data", data)
                .append("pageNo", pageNo)
                .append("pageSize", pageSize)
                .append("totalCount", totalCount)
                .toString();
    }

}
