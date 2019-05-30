/*
 * Copyright 2019 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law * or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.binaryparser.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Response model for requesting binary parser list
 * Created by yuanyoujun on 2017/9/2.
 */
public class ListBinaryParserResponse extends AbstractBceResponse {
    private List<BinaryParser> result;
    private int totalCount;
    private int pageNo;
    private int pageSize;


    public List<BinaryParser> getResult() {
        return result;
    }

    public void setResult(List<BinaryParser> result) {
        this.result = result;
    }

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
}
