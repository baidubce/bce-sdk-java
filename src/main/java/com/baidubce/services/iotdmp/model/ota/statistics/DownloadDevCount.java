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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DownloadDevCount {
    @JsonProperty("start_count")
    private Integer startCount;
    @JsonProperty("pause_count")
    private Integer pauseCount;
    @JsonProperty("resume_count")
    private Integer resumeCount;
    @JsonProperty("cancel_count")
    private Integer cancelCount;
    @JsonProperty("success_count")
    private Integer successCount;
    @JsonProperty("failed_count")
    private Integer failedCount;
}
