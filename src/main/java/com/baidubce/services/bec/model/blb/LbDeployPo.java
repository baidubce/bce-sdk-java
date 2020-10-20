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

import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Blb deployment
 */
@Data
public class LbDeployPo {

    /**
     * The name of the service.
     */
    private String serviceName;

    /**
     * id
     */
    private BigInteger id;

    /**
     * The name of the deployment
     */
    private String deploymentName;

    /**
     * User-defined name
     */
    private String customOrigName;

    /**
     * The id of the service
     */
    private String serviceId;

    /**
     * The type of the deployment
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
     * Number of Pod\Vm instances under deployment
     */
    private int replicas;

    /**
     * Pod\vm instance cpu usage
     */
    private int podCpu;

    /**
     * Pod\Vm instance memory usage
     */
    private int podMemory;

    /**
     * Pod\vm instance gpu usage
     */
    private int podGpu;

    /**
     * Pod\vm instance disk usage
     */
    private String podDataStorage;

    /**
     * The need for public IP.
     */
    private boolean podIpRequired;

    /**
     * Last billing time
     */
    private long lastBillTime;

    /**
     * Deployment creation time
     */
    private Timestamp createTime;

    /**
     * Deployment update time
     */
    private Timestamp updateTime;
}
