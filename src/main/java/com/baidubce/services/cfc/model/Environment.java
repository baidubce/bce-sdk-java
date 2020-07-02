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

import java.util.Map;

/**
 * Represent the function environment
 */
public class Environment extends JsonObject {

    /**
     * Environment variable parameter
     */
    @JsonProperty(value = "Variables")
    private Map<String, String> Variables;

    /**
     * Get the environment variable parameter
     * @return The environment variable parameter
     */
    @JsonProperty(value = "Variables")
    public Map<String, String> getVariables() {
        return this.Variables;
    }

    /**
     * Set the environment variable parameter
     * @param variables The environment variable parameter
     */
    public void setVariables(Map<String, String> variables) {
        this.Variables = variables;
    }
}
