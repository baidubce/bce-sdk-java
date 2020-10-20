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
import lombok.Data;

import java.util.List;

/**
 * VM config.
 */
@Data
public class VmConfigVo extends AbstractBceResponse {

    /**
     * The number of CPUs of the virtual machine instance.
     */
    private int cpu;

    /**
     * The memory of the virtual machine instance.
     */
    private int mem;

    /**
     * The region of the virtual machine instance.
     */
    private String region;

    /**
     * The serviceProvider of the virtual machine instance.
     */
    private String serviceProvider;

    /**
     * The city of the virtual machine instance.
     */
    private String city;

    /**
     * The need for public IP.
     */
    private boolean needPublicIp;

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
}
