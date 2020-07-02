/*
 * Copyright  2019 Baidu, Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.cfc.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

/**
 * Request object for creating a trigger for Baidu CFC function.
 */
public class CreateTriggerRequest extends AbstractBceRequest {

    /**
     * The Baidu resource name of a function
     */
    @JsonProperty("Target")
    private String Target;

    /**
     * The trigger source
     */
    @JsonProperty("Source")
    private String Source;

    /**
     * The parameter configuration of the trigger
     */
    @JsonProperty("Data")
    private Map<String, String> Data;

    /**
     * Get the target
     * @return The target
     */
    @JsonProperty(value = "Target")
    public String getTarget() {
        return this.Target;
    }

    /**
     * Set the target
     * @param target The target
     */
    public void setTarget(String target) {
        this.Target = target;
    }

    /**
     * Get the trigger source
     * @return The trigger source
     */
    @JsonProperty(value = "Source")
    public String getSource() {
        return this.Source;
    }

    /**
     * Set the trigger source
     * @param source The trigger source
     */
    public void setSource(String source) {
        this.Source = source;
    }

    /**
     * Get the parameter configuration of the trigger
     * @return The parameter configuration of the trigger
     */
    @JsonProperty("Data")
    public Map<String, String> getDate() {
        return this.Data;
    }

    /**
     * Set the parameter configuration of the trigger
     * @param data The parameter configuration of the trigger
     */
    public void setData(Map<String, String> data) {
        this.Data = data;
    }

    public CreateTriggerRequest withTarget(String target) {
        this.setTarget(target);
        return this;
    }

    public CreateTriggerRequest withSource(String source) {
        this.setSource(source);
        return this;
    }

    public CreateTriggerRequest withData(Map<String, String> data) {
        this.setData(data);
        return this;
    }

    public CreateTriggerRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        try {
            return JsonUtils.toJsonPrettyString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

}
