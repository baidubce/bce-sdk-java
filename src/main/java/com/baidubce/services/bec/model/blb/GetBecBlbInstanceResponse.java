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
package com.baidubce.services.bec.model.blb;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bec.model.Listeners;
import lombok.Data;

import java.util.List;

/**
 * The response for getting the BEC blb instance info.
 */
@Data
public class GetBecBlbInstanceResponse extends AbstractBceResponse {

    /**
     * the id of the blb.
     */
    private String blbId;

    /**
     * The name of the blb.
     */
    private String blbName;

    /**
     * The status of the blb.
     */
    private String status;

    /**
     * Load balance type.
     */
    private String lbType;

    /**
     * The region of the blb.
     */
    private String region;

    /**
     * The service provider of the blb.
     */
    private String serviceProvider;

    /**
     * The city of the blb.
     */
    private String city;

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
     * Load balancing port.
     */
    private List<Listeners> ports;

    /**
     * The number of backend servers bound to load balancing.
     */
    private int podCount;

    /**
     * Load balancing maximum bandwidth limit.
     */
    private int bandwidthInMbpsLimit;

    /**
     * Creation time.
     */
    private String createTime;

}
