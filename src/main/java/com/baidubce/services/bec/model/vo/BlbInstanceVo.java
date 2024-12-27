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
import com.baidubce.services.bec.model.Listeners;
import com.baidubce.services.bec.model.enums.ResourceStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * BEC blb instance.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlbInstanceVo extends AbstractBceResponse {

    /**
     * The id of the blb.
     */
    private String blbId;

    /**
     * The name of the blb.
     */
    private String blbName;

    /**
     * The status of the blb.
     */
    private ResourceStatusEnum status;

    /**
     * Load balance type.
     */
    private String lbType;

    /**
     * The region id.
     */
    private String regionId;

    /**
     * Public ip.
     */
    private String publicIp;

    /**
     * China mobile public ip.
     */
    private String cmPublicIP;

    /**
     * China telecom public ip.
     */
    private String ctPublicIP;

    /**
     * China unicom public ip.
     */
    private String unPublicIP;

    /**
     * Internal ip.
     */
    private String internalIp;

    /**
     * Load balance port.
     */
    private List<Listeners> ports;

    /**
     * The number of backend servers bound to load balancing.
     */
    private Integer podCount;

    /**
     * Load balance maximum bandwidth limit.
     */
    private Integer bandwidthInMbpsLimit;

    /**
     * Creation time.
     */
    private String createTime;

    /**
     * Description.
     */
    private String desc;

    /**
     * The public ipv6 address.
     */
    private String ipv6PublicIp;

    /**
     * The China Mobile public ipv6 address.
     */
    private String cmIpv6PublicIP;

    /**
     * The China Telcom ipv6 address.
     */
    private String ctIpv6PublicIP;

    /**
     * The China Unicom public ipv6 address.
     */
    private String unIpv6PublicIP;
}
