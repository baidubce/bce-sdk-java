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

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  @Description:  Response to query for cluster details
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BesClusterDetailResponse extends AbstractBceResponse implements Serializable {

    @JsonProperty
    private Boolean success;

    @JsonProperty
    private int status;

    @JsonProperty
    ClusterDetail result;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ClusterDetail getResult() {
        return result;
    }

    public void setResult(ClusterDetail result) {
        this.result = result;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ClusterDetail {
        @JsonProperty
        private String clusterId;

        @JsonProperty
        private String clusterName;

        @JsonProperty
        private String adminUsername;

        @JsonProperty
        private String status;

        @JsonProperty
        private String desireStatus;

        @JsonProperty
        private String esUrl;

        @JsonProperty
        private String kibanaUrl;

        @JsonProperty
        private String esEip;

        @JsonProperty
        private String kibanaEip;

        @JsonProperty
        private List<ModuleInfoResponse> modules;

        @JsonProperty
        private List<InstanceInfoResponse> instances;

        @JsonProperty
        private Date expireTime;

        @JsonProperty
        private String region;

        @JsonProperty
        private String vpc;

        @JsonProperty
        private String subnet;

        @JsonProperty
        private String availableZone;

        @JsonProperty
        private String securityGroup;

        @JsonProperty
        private BesClusterBillingResponse billing;

        public BesClusterBillingResponse getBilling() {
            return billing;
        }

        public void setBilling(BesClusterBillingResponse billing) {
            this.billing = billing;
        }

        public Date getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(Date expireTime) {
            this.expireTime = expireTime;
        }

        public String getClusterId() {
            return clusterId;
        }

        public void setClusterId(String clusterId) {
            this.clusterId = clusterId;
        }

        public String getClusterName() {
            return clusterName;
        }

        public void setClusterName(String clusterName) {
            this.clusterName = clusterName;
        }

        public String getAdminUsername() {
            return adminUsername;
        }

        public void setAdminUsername(String adminUsername) {
            this.adminUsername = adminUsername;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDesireStatus() {
            return desireStatus;
        }

        public void setDesireStatus(String desireStatus) {
            this.desireStatus = desireStatus;
        }

        public String getEsUrl() {
            return esUrl;
        }

        public void setEsUrl(String esUrl) {
            this.esUrl = esUrl;
        }

        public String getKibanaUrl() {
            return kibanaUrl;
        }

        public void setKibanaUrl(String kibanaUrl) {
            this.kibanaUrl = kibanaUrl;
        }

        public String getEsEip() {
            return esEip;
        }

        public void setEsEip(String esEip) {
            this.esEip = esEip;
        }

        public String getKibanaEip() {
            return kibanaEip;
        }

        public void setKibanaEip(String kibanaEip) {
            this.kibanaEip = kibanaEip;
        }

        public List<ModuleInfoResponse> getModules() {
            return modules;
        }

        public void setModules(List<ModuleInfoResponse> modules) {
            this.modules = modules;
        }

        public List<InstanceInfoResponse> getInstances() {
            return instances;
        }

        public void setInstances(List<InstanceInfoResponse> instances) {
            this.instances = instances;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getVpc() {
            return vpc;
        }

        public void setVpc(String vpc) {
            this.vpc = vpc;
        }

        public String getSubnet() {
            return subnet;
        }

        public void setSubnet(String subnet) {
            this.subnet = subnet;
        }

        public String getAvailableZone() {
            return availableZone;
        }

        public void setAvailableZone(String availableZone) {
            this.availableZone = availableZone;
        }

        public String getSecurityGroup() {
            return securityGroup;
        }

        public void setSecurityGroup(String securityGroup) {
            this.securityGroup = securityGroup;
        }

        public static class InstanceInfoResponse {
            @JsonProperty
            private String instanceId;

            @JsonProperty
            private String status;

            @JsonProperty
            private String displayActualStatus;

            @JsonProperty
            private String moduleType;

            @JsonProperty
            private String moduleDisplayName;

            @JsonProperty
            private String moduleVersion;

            @JsonProperty
            private String displayDecommissionStatus;

            @JsonProperty
            private String hostIp;

            public String getInstanceId() {
                return instanceId;
            }

            public void setInstanceId(String instanceId) {
                this.instanceId = instanceId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDisplayActualStatus() {
                return displayActualStatus;
            }

            public void setDisplayActualStatus(String displayActualStatus) {
                this.displayActualStatus = displayActualStatus;
            }

            public String getModuleType() {
                return moduleType;
            }

            public void setModuleType(String moduleType) {
                this.moduleType = moduleType;
            }

            public String getModuleDisplayName() {
                return moduleDisplayName;
            }

            public void setModuleDisplayName(String moduleDisplayName) {
                this.moduleDisplayName = moduleDisplayName;
            }

            public String getModuleVersion() {
                return moduleVersion;
            }

            public void setModuleVersion(String moduleVersion) {
                this.moduleVersion = moduleVersion;
            }

            public String getDisplayDecommissionStatus() {
                return displayDecommissionStatus;
            }

            public void setDisplayDecommissionStatus(String displayDecommissionStatus) {
                this.displayDecommissionStatus = displayDecommissionStatus;
            }

            public String getHostIp() {
                return hostIp;
            }

            public void setHostIp(String hostIp) {
                this.hostIp = hostIp;
            }
        }

        public static class ModuleInfoResponse {
            @JsonProperty
            private String type;

            @JsonProperty
            private String productVersion;

            @JsonProperty
            private String moduleDisplayName;

            @JsonProperty
            private String version;

            @JsonProperty
            private String slotType;

            @JsonProperty
            private String moduleDescription;

            @JsonProperty
            private String slotDescription;

            @JsonProperty
            private int actualInstanceNum;

            @JsonProperty
            private int desireInstanceNum;

            @JsonProperty
            private boolean isDisplay;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getProductVersion() {
                return productVersion;
            }

            public void setProductVersion(String productVersion) {
                this.productVersion = productVersion;
            }

            public String getModuleDisplayName() {
                return moduleDisplayName;
            }

            public void setModuleDisplayName(String moduleDisplayName) {
                this.moduleDisplayName = moduleDisplayName;
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

            public String getModuleDescription() {
                return moduleDescription;
            }

            public void setModuleDescription(String moduleDescription) {
                this.moduleDescription = moduleDescription;
            }

            public String getSlotDescription() {
                return slotDescription;
            }

            public void setSlotDescription(String slotDescription) {
                this.slotDescription = slotDescription;
            }

            public int getActualInstanceNum() {
                return actualInstanceNum;
            }

            public void setActualInstanceNum(int actualInstanceNum) {
                this.actualInstanceNum = actualInstanceNum;
            }

            public int getDesireInstanceNum() {
                return desireInstanceNum;
            }

            public void setDesireInstanceNum(int desireInstanceNum) {
                this.desireInstanceNum = desireInstanceNum;
            }

        }
    }
}

