/**
 * Copyright 2020 Baidu, Inc.
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
package com.baidubce.services.bes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 *  @Description:  Request to create a cluster
 */
public class BesCreateClusterRequest extends AbstractBesRequest {
    @JsonProperty
    private String name;

    @JsonProperty
    private String password;

    @JsonProperty
    private List<ModuleInfo> modules;

    @JsonProperty
    private String version;

    @JsonProperty
    private String slotType;

    @JsonProperty
    private boolean isOpenService;

    @JsonProperty
    private String availableZone;

    @JsonProperty
    private String securityGroupId;

    @JsonProperty
    private String subnetUuid;

    @JsonProperty
    private String vpcId;

    @JsonProperty
    private String serviceType;

    @JsonProperty
    private ClusterBilling billing;

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public ClusterBilling getBilling() {
        return billing;
    }

    public void setBilling(ClusterBilling billing) {
        this.billing = billing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ModuleInfo> getModules() {
        return modules;
    }

    public void setModules(List<ModuleInfo> modules) {
        this.modules = modules;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSlotType() {
        return slotType;
    }

    public void setSlotType(String slotType) {
        this.slotType = slotType;
    }

    public boolean isOpenService() {
        return isOpenService;
    }

    public void setOpenService(boolean openService) {
        isOpenService = openService;
    }

    public String getAvailableZone() {
        return availableZone;
    }

    public void setAvailableZone(String availableZone) {
        this.availableZone = availableZone;
    }

    public String getSecurityGroupId() {
        return securityGroupId;
    }

    public void setSecurityGroupId(String securityGroupId) {
        this.securityGroupId = securityGroupId;
    }

    public String getSubnetUuid() {
        return subnetUuid;
    }

    public void setSubnetUuid(String subnetUuid) {
        this.subnetUuid = subnetUuid;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    /**
     *  Configuration information for the cluster
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ModuleInfo {

        @JsonProperty
        private String type;

        @JsonProperty
        private int instanceNum;

        public int getInstanceNum() {
            return instanceNum;
        }

        public void setInstanceNum(int instanceNum) {
            this.instanceNum = instanceNum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    /**
     * Cluster payment method
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ClusterBilling {

        @JsonProperty
        private String payment;

        @JsonProperty
        private int time;

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}
