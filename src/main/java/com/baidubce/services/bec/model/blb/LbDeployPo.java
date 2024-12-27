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

import com.baidubce.services.bec.model.Backends;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * Blb deployment
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LbDeployPo {

    /**
     * The service name which the lb belong to.
     */
    private String serviceName;

    /**
     * The backends which can be bound to lb.
     */
    List<Backends> backends;

    /**
     * The name of the deployment.
     */
    private String deploymentName;

    /**
     * User-defined name.
     */
    private String customOrigName;

    /**
     * The id of the service.
     */
    private String serviceId;

    /**
     * The type of the deployment.
     */
    private String deploymentType;

    /**
     * The region of the deployment
     */
    private String region;

    /**
     * The service provider of the deployment
     */
    private String serviceProvider;

    /**
     * The city of the deployment
     */
    private String city;

    /**
     * Number of Pod\Vm instances under deployment.
     */
    private Integer replicas;

    /**
     * Pod\vm instance cpu usage.
     */
    private Integer podCpu;

    /**
     * Pod\Vm instance memory usage.
     */
    private Integer podMemory;

    /**
     * Pod\vm instance gpu usage.
     */
    private Integer podGpu;

    /**
     * Pod\vm instance disk usage.
     */
    private String podDataStorage;

    /**
     * The need for public IP.
     */
    private Boolean podIpRequired;

    /**
     * Last billing time
     */
    private long lastBillTime;

    /**
     * Whether th lb need public IPv6.
     */
    private Boolean podIpv6Required;

    /**
     * Deployment creation time.
     */
    private Timestamp createTime;

    /**
     * Deployment update time
     */
    private Timestamp updateTime;

    /**
     * The network type, classic/vpc.
     */
    private String networkType;

    /**
     * The gpu type.
     */
    private String gpuType;
}
