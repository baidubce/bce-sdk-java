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

import com.baidubce.services.bec.model.network.SecurityGroupVo;
import com.baidubce.services.bec.model.vm.KeyPair;
import com.baidubce.services.bec.model.vm.NetworkConfig;
import com.baidubce.services.bec.model.vm.SystemVolumeConfig;
import com.baidubce.services.bec.model.vm.VolumeConfig;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * Vm Instance Details.
 * Limited detail return, used in getting vm instance detail.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmInstanceDetailsVo extends VmInstanceBriefVo {

    /**
     * The count of system disks.
     */
    private Integer rootDiskSize;

    /**
     * The count of data disks.
     */
    private Integer dataStorage;

    /**
     * The data volumes of the virtual machine instance.
     */
    private List<VolumeConfig> dataVolumeList;

    /**
     * The system volume of the virtual machine instance.
     */
    private SystemVolumeConfig systemVolume;

    /**
     * The bcc key pairs of the virtual machine instance.
     */
    private List<KeyPair> bccKeyPairList;

    /**
     * The intranet private ips of the virtual machine instance.
     */
    private List<String> privateIps;

    /**
     * The virtual network configuration of the virtual machine instance.
     */
    private NetworkConfig networkConfig;

    /**
     * The security groups of the virtual machine instance.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SecurityGroupVo> securityGroups;

    /**
     * The service name of the virtual machine instance.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String serviceName;

    /**
     * The specification of the virtual machine instance.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String spec;

    /**
     * The deployment set list of the virtual machine instance.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<DeploySetVo> deploysetList;
}
