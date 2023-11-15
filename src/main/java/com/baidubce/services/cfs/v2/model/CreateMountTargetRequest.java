/*
 * Copyright 2022 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.cfs.v2.model;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateMountTargetRequest extends BaseBceRequest {
    /**
     * MountTarget所属子网，subnet属于fs所在vpc，为短id
     */
    private String subnetId;

    /**
     * fs实例vip所属VPC的短Id
     */
    private String vpcId;

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public String getSubnetId() {
        return this.subnetId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getVpcId() {
        return this.vpcId;
    }

    @Override
    public String toString() {
        return "CreateMountTargetRequest{"
                + "subnetId=" + subnetId + "\n"
                + "vpcId=" + vpcId + "\n"
                + "}";
    }

}