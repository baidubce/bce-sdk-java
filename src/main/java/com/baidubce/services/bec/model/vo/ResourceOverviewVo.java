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
import com.baidubce.services.bec.model.resource.ResourceUsageInISP;
import lombok.Data;

/**
 * Resource overview information.
 */
@Data
public class ResourceOverviewVo extends AbstractBceResponse {

    /**
     * The number of online nodes.
     */
    private int ready;

    /**
     * The number of nodes that are not online.
     */
    private int notReady;

    /**
     * The number of nodes purchased.
     */
    private int used;

    /**
     * Summary of user resource usage.
     */
    private ResourceUsageInISP resourceUsage;

    /**
     * The user has purchased the node summary.
     */
    private NodeInfoVo usedNodes;

    /**
     * Summary of online node information.
     */
    private NodeInfoVo readyNodes;

    /**
     * Summary of node information that is not online.
     */
    private NodeInfoVo notReadyNodes;
}
