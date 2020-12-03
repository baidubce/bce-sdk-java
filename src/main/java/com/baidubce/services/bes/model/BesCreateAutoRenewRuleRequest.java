/**
 * Copyright 2020 Baidu, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bes.model;

import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public class BesCreateAutoRenewRuleRequest extends AbstractBesRequest {
    @JsonProperty
    private List<String> clusterIds;
    @JsonProperty
    private String userId;
    @JsonProperty
    private String renewTimeUnit;
    @JsonProperty
    private int renewTime;

    public List<String> getClusterIds() {
        return clusterIds;
    }

    public void setClusterIds(List<String> clusterIds) {
        this.clusterIds = clusterIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRenewTimeUnit() {
        return renewTimeUnit;
    }

    public void setRenewTimeUnit(String renewTimeUnit) {
        this.renewTimeUnit = renewTimeUnit;
    }

    public int getRenewTime() {
        return renewTime;
    }

    public void setRenewTime(int renewTime) {
        this.renewTime = renewTime;
    }

    public String toJson(String region) throws IOException {

        StringWriter stringWriter = new StringWriter();

        JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(stringWriter);
        jsonGenerator.writeStartObject();
        jsonGenerator.writeArrayFieldStart("clusterIds");
        for (String clusterId : clusterIds) {
            jsonGenerator.writeString(clusterId);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeStringField("serviceType", "BES");
        jsonGenerator.writeStringField("userId", userId);
        jsonGenerator.writeStringField("region", region);
        jsonGenerator.writeStringField("renewTimeUnit", renewTimeUnit);
        jsonGenerator.writeNumberField("renewTime", renewTime);
        jsonGenerator.writeEndObject();
        jsonGenerator.close();

        return stringWriter.toString();
    }

}