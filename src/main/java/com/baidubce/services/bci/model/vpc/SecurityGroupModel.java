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
 * The model for security group
 */
public class SecurityGroupModel {

    /**
     * The id of security group
     */
    private String securityGroupId;

    /**
     * The name of security group
     */
    private String name;

    /**
     * The description of security group
     */
    private String description;

    /**
     * The vpcId of security group
     */
    private String vpcId;

    /**
     * The constructor of SecurityGroupModel
     */
    public SecurityGroupModel() {

    }

    /**
     * The constructor of SecurityGroupModel
     * @param securityGroupId The id of security group
     * @param name The name of security group
     * @param description The description of security group
     * @param vpcId The vpcId of security group
     */
    public SecurityGroupModel(String securityGroupId, String name, String description, String vpcId) {
        this.securityGroupId = securityGroupId;
        this.name = name;
        this.description = description;
        this.vpcId = vpcId;
    }

    public String getSecurityGroupId() {
        return securityGroupId;
    }

    public SecurityGroupModel setSecurityGroupId(String securityGroupId) {
        this.securityGroupId = securityGroupId;
        return this;
    }

    public String getName() {
        return name;
    }

    public SecurityGroupModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SecurityGroupModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getVpcId() {
        return vpcId;
    }

    public SecurityGroupModel setVpcId(String vpcId) {
        this.vpcId = vpcId;
        return this;
    }
}
