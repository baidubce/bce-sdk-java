/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotdmp.model.ota.statistics;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OtaTaskIssuedStatisticsResponse extends AbstractBceResponse {
    @JsonProperty("task_status")
    private String taskStatus;
    @JsonProperty("active_devtotal")
    private Integer activeDevtotal;
    @JsonProperty("active_devcount")
    private Integer activeDevcount;
    @JsonProperty("success_devcount")
    private Integer successDevcount;
    @JsonProperty("failed_devcount")
    private Integer failedDevcount;
    @JsonProperty("upgrade_total_count")
    private Integer upgradeTotalCount;
    @JsonProperty("assign_count")
    private Integer assignCount;
    @JsonProperty("deduplication_issued_count")
    private Integer deduplicationIssuedCount;
    @JsonProperty("planned_upgrade_count")
    private Integer plannedUpgradeCount;
}
