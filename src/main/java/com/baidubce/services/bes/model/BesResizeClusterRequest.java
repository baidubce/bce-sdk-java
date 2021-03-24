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

import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.StringWriter;
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
    private String paymentType;

    @JsonProperty
    private String clusterId;

    @JsonProperty
    private String region;

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        setPaymentType(paymentType.getPaymentType());
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
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

        @JsonProperty
        private DiskSlotInfo diskSlotInfo;

        public DiskSlotInfo getDiskSlotInfo() {
            return diskSlotInfo;
        }

        public void setDiskSlotInfo(DiskSlotInfo diskSlotInfo) {
            this.diskSlotInfo = diskSlotInfo;
        }

        public String getType() {
            return type;
        }

        public void setType(ModuleType type) {
            setType(type.getModuleType());
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

        public void setSlotType(SlotType slotType) {
            setSlotType(slotType.getSlotType());
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

    public String toJson(String region) throws IOException {
        StringWriter stringWriter = new StringWriter();

        JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(stringWriter);
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("clusterId", clusterId);
        jsonGenerator.writeStringField("name", name);
        jsonGenerator.writeStringField("region", region);
        jsonGenerator.writeStringField("paymentType", paymentType);
        jsonGenerator.writeArrayFieldStart("modules");
        for (BesResizeClusterRequest.ModuleDesc module : modules) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("slotType", module.getSlotType());
            jsonGenerator.writeNumberField("desireInstanceNum", module.getDesireInstanceNum());
            jsonGenerator.writeStringField("version", module.getVersion());
            jsonGenerator.writeStringField("type", module.getType());
            if (module.getDiskSlotInfo() != null) {
                jsonGenerator.writeObjectFieldStart("diskSlotInfo");
                jsonGenerator.writeStringField("type", module.getDiskSlotInfo().getType());
                jsonGenerator.writeNumberField("size", module.getDiskSlotInfo().getSize());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
        jsonGenerator.close();

        return stringWriter.toString();
    }

}
