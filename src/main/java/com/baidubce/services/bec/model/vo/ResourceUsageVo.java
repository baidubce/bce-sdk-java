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

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bec.model.resource.ResourceCount;
import lombok.Data;

/**
 * Summary of user container usage.
 */
@Data
public class ResourceUsageVo extends AbstractBceResponse {

    /**
     * Service overview quantity summary.
     */
    private ResourceCount serviceCount;

    /**
     * Summary of deployment overview quantity.
     */
    private ResourceCount deploymentCount;

    /**
     * Summary of the number of pod overviews.
     */
    private ResourceCount instanceCount;

    /**
     * Summary of current cpu usage.
     */
    private int cpu;

    /**
     * Summary of current memory usage.
     */
    private int memory;

    /**
     * Summary of current gpu usage.
     */
    private int gpu;

    /**
     * Summary of current bandwidth usage.
     */
    private long bandwidth;
}
