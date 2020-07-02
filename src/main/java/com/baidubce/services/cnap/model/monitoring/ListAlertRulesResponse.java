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

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * The reponse for list alert rules.
 */
public class ListAlertRulesResponse extends AbstractBceResponse {

    /**
     * The page size of list.
     */
    private int pageSize = 10;

    /**
     * The page no of list.
     */
    private int pageNo = 1;

    /**
     * The total count.
     */
    private int totalCount;

    /**
     * The result info.
     */
    private List<AlertRulesModel> result;

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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<AlertRulesModel> getResult() {
        return result;
    }

    public void setResult(List<AlertRulesModel> result) {
        this.result = result;
    }
}
