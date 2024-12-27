/*
 * Copyright (c) 2024 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bec.model.vm.deployset;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Author zhangyongchao01
 * @Since 2024-11-07 12:09
 * @Version v1.0
 * The statistics of node instance.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NodeInstanceStatis {

    /**
     * The region id of the deployment set.
     */
    private String regionId;

    /**
     * The instance count of the deployment set.
     */
    private Integer instanceCount;

    /**
     * The maximum instance of the deployment set.
     */
    private Integer instanceTotal;

    /**
     * The instance ids of the deployment set.
     */
    private List<String> instanceIds;
}
