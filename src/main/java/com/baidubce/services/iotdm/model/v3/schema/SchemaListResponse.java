/*
 * Copyright 2017 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotdm.model.v3.schema;

import com.baidubce.model.AbstractBceResponse;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Represent the response of getting a schema list.
 */
public class SchemaListResponse extends AbstractBceResponse {

    private int totalCount;

    private int pageNo;

    private int pageSize;

    private List<Schema> result = Lists.newArrayList();

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
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

    public List<Schema> getResult() {
        return result;
    }

    public void setResult(List<Schema> result) {
        this.result = result;
    }

}
