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
package com.baidubce.services.bec.model.vo;

import com.baidubce.services.bec.model.enums.RouteTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * RouteRuleVo
 *
 * @Author zhangyongchao01
 * @Since 2024-11-12 19:08
 * @Version v1.0
 * <p>
 * The route rule.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouteRuleVo {

    /**
     * The route table id.
     */
    private String tableId;

    /**
     * The route rule id.
     */
    private String ruleId;

    /**
     * The status of the route table.
     */
    private String status;

    /**
     * The ip versions.
     */
    private Integer ipVersion;

    /**
     * The source address.
     */
    private String sourceAddress;

    /**
     * The destination address.
     */
    private String destinationAddress;

    /**
     * The route type.
     */
    private RouteTypeEnum routeType;

    /**
     * The next hop.
     */
    private String nexthop;

    /**
     * The description.
     */
    private String description;
}