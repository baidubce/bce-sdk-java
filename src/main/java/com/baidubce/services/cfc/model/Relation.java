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

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Relation
 */
public class Relation {
    /**
     * Trigger ID
     */
    @JsonProperty(value = "RelationId")
    private String RelationId;

    /**
     * policy statement-id
     */
    @JsonProperty(value = "Sid")
    private String Sid;

    /**
     * Trigger source
     */
    @JsonProperty(value = "Source")
    private String Source;

    /**
     * Function BRN
     */
    @JsonProperty(value = "Target")
    private String Target;

    /**
     * Data
     */
    @JsonProperty(value = "Data")
    private RelationConfiguration Data;

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
     * Get the Sid
     * @return The Sid
     */
    @JsonProperty(value = "Sid")
    public String getSid() {
        return this.Sid;
    }

    /**
     * Set the Sid
     * @param sid The Sid
     */
    public void setSid(String sid) {
        this.Sid = sid;
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
     * Get the data
     * @return The data
     */
    @JsonProperty(value = "Data")
    public RelationConfiguration getData() {
        return this.Data;
    }

    /**
     * Set the data
     * @param data The data
     */
    public void setData(RelationConfiguration data) {
        this.Data = data;
    }
}

