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
import com.baidubce.services.bec.model.vm.SystemVolumeConfig;
import com.baidubce.services.bec.model.vm.VolumeConfig;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * VM config.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmConfigVo extends AbstractBceResponse {

    /**
     * The number of CPUs of the virtual machine instance.
     */
    private Integer cpu;

    /**
     * The memory of the virtual machine instance.
     */
    private Integer mem;

    /**
     * The need for public IP.
     */
    private Boolean needPublicIp;

    /**
     * The bandwidth of the virtual machine instance.
     */
    private String bandwidth;

    /**
     * System image details.
     */
    private ImageDetail osImage;

    /**
     * Data disk configuration list.
     */
    private List<VolumeConfig> dataVolumeList;

    /**
     * System disk configuration information.
     */
    private SystemVolumeConfig systemVolume;

    /**
     * The region id of the virtual machine instance.
     */
    private String regionId;

    /**
     * The specification of the virtual machine instance.
     */
    private String spec;

    /**
     * The gpu card count of the virtual machine instance.
     */
    private Integer gpu;

    /**
     * The gpu type of the virtual machine instance, like nvidia.com/GA102_GeForce_RTX_3080.
     */
    private String gpuType;

    /**
     * The gpu model of the virtual machine instance, like NVIDIA GeForce RTX 3080.
     */
    private String gpuModel;
}
