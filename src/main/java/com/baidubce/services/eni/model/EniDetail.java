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

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * The detail model for describing Elastic Network Interface Card.
 */
@Getter
@Setter
@ToString(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class EniDetail extends Eni {

    /**
     * List of security group IDs bound to the ENI
     */
    private List<String> securityGroupIds;

    private List<String> enterpriseSecurityGroupIds;
    /**
     * createTime of ENI
     */
    private String createdTime;

    @Builder(builderMethodName = "EniDetailBuilder")
    public EniDetail(String eniId, String name, String zoneName, String description, String instanceId,
                     String macAddress, String vpcId, String subnetId, String status, List<PrivateIp> privateIpSet,
                     List<String> securityGroupIds) {
        super(eniId, name, zoneName, description, instanceId, macAddress, vpcId, subnetId, status, privateIpSet);
        this.securityGroupIds = securityGroupIds;
    }
}
