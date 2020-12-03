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

public class BesUpdateAutoRenewRuleRequest extends AbstractBesRequest {
    @JsonProperty
    private String clusterId;
    @JsonProperty
    private String renewTimeUnit;
    @JsonProperty
    private int renewTime;

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
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

    public String toJson() throws IOException {
        StringWriter stringWriter = new StringWriter();

        JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(stringWriter);
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("clusterId", clusterId);
        jsonGenerator.writeStringField("renewTimeUnit", renewTimeUnit);
        jsonGenerator.writeNumberField("renewTime", renewTime);
        jsonGenerator.writeEndObject();
        jsonGenerator.close();

        return stringWriter.toString();
    }

}