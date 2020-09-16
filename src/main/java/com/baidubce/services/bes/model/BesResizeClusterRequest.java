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

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *  @Description:  Request to resize a cluster
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BesResizeClusterRequest extends AbstractBesRequest {

    @JsonProperty
    private String name;

    @JsonProperty
    private List<ModuleDesc> modules;

    @NotNull
    @JsonProperty
    private List<BesConfigTuple> configs;

    @JsonProperty
    private String productType;

    @JsonProperty
    private String deployId;

    @JsonProperty
    private String region;

    public String getDeployId() {
        return deployId;
    }

    public void setDeployId(String deployId) {
        this.deployId = deployId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<ModuleDesc> getModules() {
        return modules;
    }

    public void setModules(List<ModuleDesc> modules) {
        this.modules = modules;
    }

    public List<BesConfigTuple> getConfigs() {
        return configs;
    }

    public void setConfigs(List<BesConfigTuple> configs) {
        this.configs = configs;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public static class ModuleDesc {

        @JsonProperty
        private String type;

        @JsonProperty
        private String version;

        @JsonProperty("slot_type")
        private String slotType;

        @JsonProperty
        private int desireInstanceNum;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public int getDesireInstanceNum() {
            return desireInstanceNum;
        }

        public void setDesireInstanceNum(int desireInstanceNum) {
            this.desireInstanceNum = desireInstanceNum;
        }
    }
}
