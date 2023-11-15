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
import com.baidubce.services.iotdmp.model.ota.packages.OtaPackage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOtaTaskRequest extends GenericAccountRequest {
    private Basic basic;
    @JsonProperty("package_data")
    private PackageData packageData;
    private Policy policy;
    @JsonProperty("target_set")
    private TargetSet targetSet;
    @JsonProperty("delivery_config")
    private DeliveryConfig deliveryConfig;
    @JsonProperty("origin_task_id")
    private Integer originTakId;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Basic {
        private String tname;
        private String comment;
        private Integer priority;
        private Type type;

        public enum Type {
            online, test
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PackageData {
        @JsonProperty("package_type")
        private String packageType;
        @JsonProperty("upgrade_type")
        private String upgradeType;
        private List<OtaPackage> list;
    }
}
