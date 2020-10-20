/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bec.model.vo;

import com.baidubce.services.bec.model.Stats;
import lombok.Data;

import java.util.List;

/**
 * Blb Backend Pod Brief.
 */
@Data
public class BlbBackendPodBriefVo {

    /**
     * The name of the Pod/Vm.
     */
    private String podName;

    /**
     * The status of the Pod/Vm.
     */
    private String podStatus;

    /**
     * The ip of the Pod/Vm.
     */
    private String podIp;

    /**
     * The port of the Pod/Vm.
     */
    private List<Stats> backendPort;

    /**
     * The weight of the Pod/Vm.
     */
    private int weight;
}
