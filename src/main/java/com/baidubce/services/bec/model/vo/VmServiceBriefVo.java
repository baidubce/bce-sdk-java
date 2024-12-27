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
import com.baidubce.services.bec.model.ImageDetail;
import com.baidubce.services.bec.model.enums.ResourceStatusEnum;
import com.baidubce.services.bec.model.purchase.DeploymentInstance;
import com.baidubce.services.tag.model.Tag;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * Vm Service Brief.
 * Limited brief content return.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmServiceBriefVo extends AbstractBceResponse {

    /**
     * The id of the BEC virtual machine service.
     */
    private String serviceId;

    /**
     * The name of the BEC virtual machine service.
     */
    private String serviceName;

    /**
     * The status of the BEC virtual machine service.
     */
    private ResourceStatusEnum status;

    /**
     * The payment method of the BEC virtual machine service.
     */
    private String paymentMethod;

    /**
     * Total number of cpu.
     */
    private Integer totalCpu;

    /**
     * Total number of memory.
     */
    private Integer totalMem;

    /**
     * The total number of data disks.
     */
    private Integer totalDisk;

    /**
     * The total number of system disks.
     */
    private Integer totalRootDisk;

    /**
     * Number of regions.
     */
    private Integer regions;

    /**
     * List of deployment instances.
     */
    private List<DeploymentInstance> deployInstances;

    /**
     * The total number of instances.
     */
    private Integer totalInstances;

    /**
     * Number of running instances.
     */
    private Integer runningInstances;

    /**
     * System image details.
     */
    private ImageDetail osImage;

    /**
     * The simple instances info of the BEC virtual machine service.
     */
    private List<VmInstanceIdVo> instances;

    /**
     * Creation time.
     */
    private String createTime;

    /**
     * The order ids of the BEC virtual machine service.
     */
    private List<String> orderIdList;

    /**
     * The tags of the BEC virtual machine service.
     */
    private List<Tag> tags;
}