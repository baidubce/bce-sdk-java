/*
 * Copyright (c) 2014-2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.eni.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * The model for describing Elastic Network Interface Card.
 */
@Getter
@Setter
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Eni extends AbstractBceResponse {
    /**
     * The id of ENI
     */
    private String eniId;

    /**
     * The name of ENI
     */
    private String name;

    /**
     * Availability zone to which the ENI belongs
     */
    private String zoneName;

    /**
     * The description of ENI
     */
    private String description;

    /**
     * The id of the cloud host mounted on the ENI
     */
    private String instanceId;

    /**
     * The mac address of the ENI
     */
    private String macAddress;

    /**
     * The vpc ID of the ENI
     */
    private String vpcId;

    /**
     * The subnet ID of the ENI
     */
    private String subnetId;

    /**
     * The status of ENI
     * available:not mounted
     * attaching:is being mounted
     * inuse:has been mounted
     * detaching:is being detached
     */
    private String status;

    /**
     * Intranet IP information
     * Only one of the specified IPs can be the primary IP, and the others must be secondary IPs.
     */
    private List<PrivateIp> privateIpSet;
}
