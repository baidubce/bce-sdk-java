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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Vm Instance Brief.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VmInstanceBriefVo extends AbstractBceResponse {

    /**
     * Single-line public ip.
     */
    private String publicIp;

    /**
     * Internal ip.
     */
    private String internalIp;

    /**
     * Triple-line public ip.
     */
    private List<IpInfo> multiplePublicIp;

    /**
     * Service provider.
     */
    private String serviceProvider;

    /**
     * The id of the virtual machine instance.
     */
    private String vmId;

    /**
     * The name of the virtual machine instance.
     */
    private String vmName;

    /**
     * The status of the virtual machine instance.
     */
    private String status;

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
     * The id of the virtual machine service.
     */
    private String serviceId;

    /**
     * Creation time.
     */
    private String createTime;

    /**
     * The spec of the virtual machine instance.
     */
    private String spec;

    /**
     * The regionId of the virtual machine instance.
     */
    private String regionId;

    /**
     * The vpc of the virtual machine instance.
     */
    private VpcVo vpc;

    /**
     * The needIpv6PublicIp of the virtual machine instance.
     */
    private boolean needIpv6PublicIp;

    /**
     * The hostname of the virtual machine instance.
     */
    private String hostname;

    @Data
    public static class IpInfo {
        /**
         * Service provider.
         */
        private String serviceProvider;

        /**
         * Public ip.
         */
        private String ip;
    }
}
