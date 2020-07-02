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
 * Request object for updating the trigger
 */
public class UpdateTriggerRequest extends AbstractBceRequest {
    /**
     * Trigger ID
     */
    @JsonProperty(value = "RelationId")
    private String RelationId;

    /**
     * Function BRN
     */
    @JsonProperty(value = "Target")
    private String Target;

    /**
     * Trigger source
     */
    @JsonProperty(value = "Source")
    private String Source;

    /**
     * Trigger parameter configuration
     */
    @JsonProperty(value = "Data")
    private Map<String, String> Data;

    /**
     * Get the relation Id
     * @return The relation Id
     */
    @JsonProperty(value = "RelationId")
    public String getRelationId() {
        return this.RelationId;
    }

    /**
     * Set the relation Id
     * @param relationId The relation Id
     */
    public void setRelationId(String relationId) {
        this.RelationId = relationId;
    }

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
     * Get the source
     * @return The source
     */
    @JsonProperty(value = "Source")
    public String getSource() {
        return this.Source;
    }

    /**
     * Set the source
     * @param source The source
     */
    public void setSource(String source) {
        this.Source = source;
    }

    /**
     * Get the data
     * @return The data
     */
    @JsonProperty(value = "Data")
    public Map<String, String> getDate() {
        return this.Data;
    }

    /**
     * Set the data
     * @param data The data
     */
    public void setData(Map<String, String> data) {
        this.Data = data;
    }

    public UpdateTriggerRequest withRelationId(String relationId) {
        this.setRelationId(relationId);
        return this;
    }

    public UpdateTriggerRequest withTarget(String target) {
        this.setTarget(target);
        return this;
    }

    public UpdateTriggerRequest withSource(String source) {
        this.setSource(source);
        return this;
    }

    public UpdateTriggerRequest withData(Map<String, String> data) {
        this.setData(data);
        return this;
    }

    public UpdateTriggerRequest withRequestCredentials(BceCredentials credentials) {
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

