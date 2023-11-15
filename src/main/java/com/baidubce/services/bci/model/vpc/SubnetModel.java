/*
 * Copyright (c) 2023 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.bci.model.vpc;

/**
 * The model for subnet
 */
public class SubnetModel {

    /**
     * The id of subnet
     */
    private String subnetId;

    /**
     * The name of subnet
     */
    private String name;

    /**
     * The cidr of subnet
     */
    private String cidr;

    /**
     * The vpcId of subnet
     */
    private String vpcId;

    /**
     * The subnetType of subnet
     */
    private String subnetType;

    /**
     * The createTime of subnet
     */
    private String description;

    /**
     * The createTime of subnet
     */
    private String createTime;

    /**
     * The constructor of SubnetModel
     */
    public SubnetModel() {

    }

    /**
     * The constructor of SubnetModel
     * @param subnetId The id of subnet
     * @param name The name of subnet
     * @param cidr The cidr of subnet
     * @param vpcId The vpcId of subnet
     * @param subnetType The subnetType of subnet
     * @param description The description of subnet
     * @param createTime The createTime of subnet
     */
    public SubnetModel(String subnetId, String name, String cidr, String vpcId, String subnetType, String description,
            String createTime) {
        this.subnetId = subnetId;
        this.name = name;
        this.cidr = cidr;
        this.vpcId = vpcId;
        this.subnetType = subnetType;
        this.description = description;
        this.createTime = createTime;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public SubnetModel setSubnetId(String subnetId) {
        this.subnetId = subnetId;
        return this;
    }

    public String getName() {
        return name;
    }

    public SubnetModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getCidr() {
        return cidr;
    }

    public SubnetModel setCidr(String cidr) {
        this.cidr = cidr;
        return this;
    }

    public String getVpcId() {
        return vpcId;
    }

    public SubnetModel setVpcId(String vpcId) {
        this.vpcId = vpcId;
        return this;
    }

    public String getSubnetType() {
        return subnetType;
    }

    public SubnetModel setSubnetType(String subnetType) {
        this.subnetType = subnetType;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SubnetModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public SubnetModel setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }
}
