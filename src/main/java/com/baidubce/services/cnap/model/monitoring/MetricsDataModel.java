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

/**
 * The model for metrics data.
 */
public class MetricsDataModel {

    /**
     * The data type.
     */
    private String resultType;

    /**
     * The result list.
     */
    private List<MetricsResultModel> result;

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public List<MetricsResultModel> getResult() {
        return result;
    }

    public void setResult(List<MetricsResultModel> result) {
        this.result = result;
    }
}
