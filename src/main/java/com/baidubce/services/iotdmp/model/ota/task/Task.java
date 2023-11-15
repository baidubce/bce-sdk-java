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
package com.baidubce.services.iotdmp.model.ota.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {
    private Basic basic;
    @JsonProperty("package_data")
    private PackageData packageData;
    @JsonProperty("test_info")
    private TaskInfo testInfo;
    @JsonProperty("issue_info")
    private TaskInfo issueInfo;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TaskInfo {
        @JsonProperty("amount_total")
        private Integer amountTotal;
        @JsonProperty("amount_success")
        private Integer amountSuccess;
        @JsonProperty("amount_failed")
        private Integer amountFailed;
    }
}
