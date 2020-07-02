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

package com.baidubce.services.cnap.model.deploygroup;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * The model for resource requirement.
 */
public class ResourceRequirementsModel {
    /**
     * The resource requirement info.
     */
    private Map<String, String> requests = new HashMap<String, String>();

    public Map<String, String> getRequests() {
        return requests;
    }

    public ResourceRequirementsModel withCpuRequirement(double cpu) {
        BigDecimal bg = BigDecimal.valueOf(cpu);
        double d = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        requests.put("cpu", String.valueOf((int)(1000 * d)) + "m");
        return this;
    }

    public ResourceRequirementsModel withMemoryRequirement(int memory) {
        requests.put("memory", String.valueOf(memory) + "Mi");
        return this;
    }

    public Map<String, String> requestsInfo() {
        return this.requests;
    }

}
