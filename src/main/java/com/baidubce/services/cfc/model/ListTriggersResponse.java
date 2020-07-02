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

import java.util.ArrayList;
import java.util.List;

/**
 * Response object for listing triggers for the CFC function
 */
public class ListTriggersResponse extends CfcResponse {

    /**
     * Relation
     */
    @JsonProperty(value = "Relation")
    private List<Relation> Relation = new ArrayList<Relation>();

    /**
     * Get the relation
     * @return The relation
     */
    @JsonProperty(value = "Relation")
    public List<Relation> getRelation() {
        return this.Relation;
    }

    /**
     * Set the relation
     * @param relation The relation
     */
    public void setRelation(List<Relation> relation) {
        this.Relation = relation;
    }

}

