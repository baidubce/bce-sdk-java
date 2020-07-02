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

/**
 * Request object for deleting a trigger of the Baidu CFC function
 */
public class DeleteTriggerRequest extends AbstractBceRequest {
    /**
     * The function BRN
     */
    @JsonProperty(value = "Target")
    private String Target;

    /**
     * The trigger source
     */
    @JsonProperty(value = "Source")
    private String Source;

    /**
     * The trigger ID
     */
    @JsonProperty(value = "RelationId")
    private String RelationId;

    /**
     * Get the function BRN
     * @return The function BRN
     */
    @JsonProperty(value = "Target")
    public String getTarget() {
        return this.Target;
    }

    /**
     * Set the function BRN
     * @param target The function BRN
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
     * Get the trigger ID
     * @return The trigger ID
     */
    @JsonProperty(value = "RelationId")
    public String getRelationId() {
        return this.RelationId;
    }

    /**
     * Set the trigger ID
     * @param relationId The trigger ID
     */
    public void setRelationId(String relationId) {
        this.RelationId = relationId;
    }

    public DeleteTriggerRequest withTarget(String target) {
        this.setTarget(target);
        return this;
    }

    public DeleteTriggerRequest withSource(String source) {
        this.setSource(source);
        return this;
    }

    public DeleteTriggerRequest withRelationId(String relationId) {
        this.setRelationId(relationId);
        return this;
    }

    public DeleteTriggerRequest withRequestCredentials(BceCredentials credentials) {
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
