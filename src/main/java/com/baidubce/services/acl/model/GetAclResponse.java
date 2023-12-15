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

import com.baidubce.model.AbstractBceResponse;

/**
 * The response for GetAclRequest.
 */
public class GetAclResponse extends AbstractBceResponse {

    /**
     * the vpc id of the acl
     */
    private String vpcId;

    /**
     * the vpc name of the acl
     */
    private String vpcName;

    /**
     * the vpc cidr of the acl
     */
    private String vpcCidr;

    /**
     * the acl entry detail info
     */
    private List<AclEntry> aclEntrys;

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getVpcName() {
        return vpcName;
    }

    public void setVpcName(String vpcName) {
        this.vpcName = vpcName;
    }

    public String getVpcCidr() {
        return vpcCidr;
    }

    public void setVpcCidr(String vpcCidr) {
        this.vpcCidr = vpcCidr;
    }

    public List<AclEntry> getAclEntrys() {
        return aclEntrys;
    }

    public void setAclEntrys(List<AclEntry> aclEntrys) {
        this.aclEntrys = aclEntrys;
    }

    @Override
    public String toString() {
        return "GetAclResponse{" +
                "vpcId='" + vpcId + '\'' +
                ", vpcName='" + vpcName + '\'' +
                ", vpcCidr='" + vpcCidr + '\'' +
                ", aclEntrys=" + aclEntrys +
                '}';
    }
}
