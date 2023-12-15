/*
 * Copyright 2023 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.lbdc.model;

import java.util.List;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBoundBlBListOfLbdcResponse extends BaseBceResponse {
    /**
     * 集群id
     */
    private List<AssociateBlbModel> blbList;

    public void setBlbList(List<AssociateBlbModel> blbList) {
        this.blbList = blbList;
    }

    public List<AssociateBlbModel> getBlbList() {
        return this.blbList;
    }

    @Override
    public String toString() {
        return "GetBoundBlBListOfLbdcResponse{"
                + "blbList=" + blbList + "\n"
                + "}";
    }

    public static class AssociateBlbModel {
        private String blbId;
    
        private String name;
    
        private String type;
    
        private String blbType;
    
        private Integer bandwidth;
    
        private String address;
    
        private String ipv6;
    
        private String vpcId;
    
        private String subnetId;
    
        public void setBlbId(String blbId) {
            this.blbId = blbId;
        }
    
        public String getBlbId() {
            return this.blbId;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getName() {
            return this.name;
        }
    
        public void setType(String type) {
            this.type = type;
        }
    
        public String getType() {
            return this.type;
        }
    
        public void setBlbType(String blbType) {
            this.blbType = blbType;
        }
    
        public String getBlbType() {
            return this.blbType;
        }
    
        public void setBandwidth(Integer bandwidth) {
            this.bandwidth = bandwidth;
        }
    
        public Integer getBandwidth() {
            return this.bandwidth;
        }
    
        public void setAddress(String address) {
            this.address = address;
        }
    
        public String getAddress() {
            return this.address;
        }
    
        public void setIpv6(String ipv6) {
            this.ipv6 = ipv6;
        }
    
        public String getIpv6() {
            return this.ipv6;
        }
    
        public void setVpcId(String vpcId) {
            this.vpcId = vpcId;
        }
    
        public String getVpcId() {
            return this.vpcId;
        }
    
        public void setSubnetId(String subnetId) {
            this.subnetId = subnetId;
        }
    
        public String getSubnetId() {
            return this.subnetId;
        }
    
        @Override
        public String toString() {
            return "AssociateBlbModel{"
                    + "blbId=" + blbId + "\n"
                    + "name=" + name + "\n"
                    + "type=" + type + "\n"
                    + "blbType=" + blbType + "\n"
                    + "bandwidth=" + bandwidth + "\n"
                    + "address=" + address + "\n"
                    + "ipv6=" + ipv6 + "\n"
                    + "vpcId=" + vpcId + "\n"
                    + "subnetId=" + subnetId + "\n"
                    + "}";
        }
    }

}