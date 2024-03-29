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
 * @date 2021/5/28
 * @desc get the contact template infos response object
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListTemplateInfoResponse extends AbstractBceResponse {

    /**
     * the total result count num about the contact template infos.
     */
    private int totalCount;

    /**
     * the detail infos
     */
    private List<GetTemplateInfoResponse> result;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<GetTemplateInfoResponse> getResult() {
        return result;
    }

    public void setResult(List<GetTemplateInfoResponse> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("totalCount", totalCount)
                .add("result", result)
                .toString();
    }
}
