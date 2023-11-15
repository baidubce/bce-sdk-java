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

import com.baidubce.model.GenericAccountRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOtaTaskRequest extends GenericAccountRequest {
    private Integer priority;
    private Notify notify;
    private DailySchedule dailySchedule;
    private String userData;
    private DeviceGroup deviceGroup;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Notify {
        private Boolean forceUpgrade;
        private String releaseNote;
        private Integer silenceUpdateType;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DailySchedule {
        @JsonProperty("switch")
        private Boolean isSwitch;
        private String startTime;
        private String stopTime;
        private Boolean autoCheckUpdateOnly;
    }
}
