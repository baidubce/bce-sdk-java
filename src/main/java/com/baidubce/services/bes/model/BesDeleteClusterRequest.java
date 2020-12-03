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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.StringWriter;

/**
 *  @Description:  The request for delete cluster
 */
public class BesDeleteClusterRequest extends AbstractBesRequest {

    @JsonProperty
    private String clusterId;

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String toJson() throws IOException {
        StringWriter stringWriter = new StringWriter();

        JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(stringWriter);
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("clusterId", clusterId);
        jsonGenerator.writeEndObject();
        jsonGenerator.close();

        return stringWriter.toString();
    }
}
