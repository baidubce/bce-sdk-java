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
 * The model for vpc
 */
public class VpcModel {

    /**
     * The id of vpc
     */
    private String vpcId;

    /**
     * The name of vpc
     */
    private String name;

    /**
     * The cidr of vpc
     */
    private String cidr;

    /**
     * The createTime of vpc
     */
    private String createTime;

    /**
     * The description of vpc
     */
    private String description;

    /**
     * The isDefault of vpc
     */
    private Boolean isDefault;

    /**
     * The constructor of VpcModel
     */
    public VpcModel() {

    }

    /**
     * The constructor of VpcModel
     * @param vpcId The id of vpc
     * @param name The name of vpc
     * @param cidr The cidr of vpc
     * @param createTime The createTime of vpc
     * @param description The description of vpc
     * @param isDefault The isDefault of vpc
     */
    public VpcModel(String vpcId, String name, String cidr, String createTime, String description, Boolean isDefault) {
        this.vpcId = vpcId;
        this.name = name;
        this.cidr = cidr;
        this.createTime = createTime;
        this.description = description;
        this.isDefault = isDefault;
    }

    public String getVpcId() {
        return vpcId;
    }

    public VpcModel setVpcId(String vpcId) {
        this.vpcId = vpcId;
        return this;
    }

    public String getName() {
        return name;
    }

    public VpcModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getCidr() {
        return cidr;
    }

    public VpcModel setCidr(String cidr) {
        this.cidr = cidr;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public VpcModel setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public VpcModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public VpcModel setDefault(Boolean aDefault) {
        isDefault = aDefault;
        return this;
    }
}
