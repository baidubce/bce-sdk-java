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
package com.baidubce.services.iotdmp.model.service;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.iotdmp.model.extension.ability.ServiceType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListInstanceServiceStateResponse extends AbstractBceResponse {
    private List<ServiceStateItem> result;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ServiceStateItem {
        private ServiceType serviceType;
        private boolean state;
        private String failureCause;
        private String failureStartTime;
        private String failureRecoverTime;
    }
}
