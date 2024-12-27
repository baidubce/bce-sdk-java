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

import com.baidubce.services.bec.model.ImageDetail;
import com.baidubce.services.tag.model.Tag;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Vm Instance Brief.
 * Limited brief content return.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VmInstanceBriefVo extends IpPackageVo {

    /**
     * The id of the virtual machine instance.
     */
    private String vmId;

    /**
     * The long resource uuid.
     */
    private String uuid;

    /**
     * The name of the virtual machine instance.
     */
    private String vmName;

    /**
     * The hostname of the virtual machine instance.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hostname;

    /**
     * The status of the virtual machine instance.
     */
    private String status;

    /**
     * The number of CPUs of the virtual machine instance.
     */
    private Integer cpu;

    /**
     * The memory of the virtual machine instance.
     */
    private Integer mem;

    /**
     * The number of GPUs the virtual machine instance.
     */
    private Integer gpu;

    /**
     * The same as GpuFlavor front end.
     */
    private String gpuModel;

    /**
     * The region id of the virtual machine instance.
     */
    private String regionId;

    /**
     * Whether the virtual machine need public ip.
     */
    private Boolean needPublicIp;

    /**
     * Whether the virtual machine need public ipv6.
     */
    private Boolean needIpv6PublicIp;

    /**
     * The bandwidth of the virtual machine instance.
     */
    private String bandwidth;

    /**
     * System image details.
     */
    private ImageDetail osImage;

    /**
     * The id of the virtual machine service.
     */
    private String serviceId;

    /**
     * The payment method of the virtual machine.
     */
    private String paymentMethod;

    /**
     * The reservation time length of the prepayment purchase.
     */
    private Integer reserveLength;

    /**
     * The renewal cycle of the prepayment purchase.
     */
    private Integer renewCycle;

    /**
     * The order ids of the virtual machine instance.
     */
    private List<String> orderIdList;

    /**
     * The gpu flavor.
     */
    private String gpuFlavor;

    /**
     * Creation time.
     */
    private String createTime;

    /**
     * The deployment id of the virtual machine instance.
     */
    private String deploymentId;

    /**
     * The userData of the virtual machine instance.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userData;

    /**
     * The dns of the virtual machine instance.
     */
    private String dns;

    /**
     * The vpc of the virtual machine instance.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VpcVo vpc;

    /**
     * The subnet id of the virtual machine instance.
     */
    private String subnetId;

    /**
     * The security group ids of the virtual machine instance.
     */
    private String sgIds;

    /**
     * The deployment set id of the virtual machine instance.
     */
    private String deploysetId;

    /**
     * The tags of the virtual machine instance.
     */
    private List<Tag> tags;

    /**
     * The cuda version of the virtual machine instance.
     */
    private String cudaVersion;

    /**
     * The driver version of the virtual machine instance.
     */
    private String driverVersion;

    /**
     * The cuDNN version of the virtual machine instance.
     */
    private String cudnnVersion;
}
