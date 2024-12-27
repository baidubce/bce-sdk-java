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
package com.baidubce.services.bec.model.vo.v2;

import com.baidubce.services.bec.model.appblbv2.listener.AppRule;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Author zhangyongchao01
 * @Since 2024-11-21 20:14
 * @Version v1.0
 * <p>
 * App policy.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppPolicyVo {

    /**
     * The policy id.
     */
    private String id;

    /**
     * The ip group id.
     */
    private String appIpGroupId;

    /**
     * The ip group name.
     */
    private String appIpGroupName;

    /**
     * The description.
     */
    private String desc;

    /**
     * The frontend port.
     */
    private Integer frontendPort;

    /**
     * The priority.
     */
    private Integer priority;

    /**
     * The rule List.
     */
    private List<AppRule> ruleList;
}