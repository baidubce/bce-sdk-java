/*
 * Copyright 2023 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.lbdc.model;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpgradeLbdcRequest extends BaseBceRequest {
    /**
     * 集群性能容量单位CCU（Cluster Capacity Unit）是用来衡量BLB集群处理流量时涉及的各个指标。
     */
    private Integer ccuCount;

    public Integer getCcuCount() {
        return ccuCount;
    }

    public void setCcuCount(Integer ccuCount) {
        this.ccuCount = ccuCount;
    }

    @Override
    public String toString() {
        return "UpgradeLbdcRequest{"
                + "ccuCount=" + ccuCount + "\n"
                + "}";
    }

}