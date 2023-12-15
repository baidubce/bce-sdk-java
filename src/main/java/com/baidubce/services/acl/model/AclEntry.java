/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.acl.model;

import java.util.List;

/**
 * acl detail info model
 */
public class AclEntry {

    /**
     * the subnetId of the acl
     */
    private String subnetId;

    /**
     * the subnetName of the acl
     */
    private String subnetName;

    /**
     * the subnetCidr of the acl
     */
    private String subnetCidr;

    /**
     * the rules of the acl
     */
    private List<AclRule> aclRules;

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public String getSubnetName() {
        return subnetName;
    }

    public void setSubnetName(String subnetName) {
        this.subnetName = subnetName;
    }

    public String getSubnetCidr() {
        return subnetCidr;
    }

    public void setSubnetCidr(String subnetCidr) {
        this.subnetCidr = subnetCidr;
    }

    public List<AclRule> getAclRules() {
        return aclRules;
    }

    public void setAclRules(List<AclRule> aclRules) {
        this.aclRules = aclRules;
    }

    @Override
    public String toString() {
        return "AclEntry{" +
                "subnetId='" + subnetId + '\'' +
                ", subnetName='" + subnetName + '\'' +
                ", subnetCidr='" + subnetCidr + '\'' +
                ", aclRules=" + aclRules +
                '}';
    }
}
