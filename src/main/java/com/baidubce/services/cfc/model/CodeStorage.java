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
 * Represent the store information of function code
 */
public class CodeStorage extends JsonObject {
    /**
     * The stored address of function address
     */
    @JsonProperty(value = "Location")
    private String Location;

    /**
     * Represent the log storage type, currently supports Bos
     */
    @JsonProperty(value = "RepositoryType")
    private String RepositoryType;

    /**
     * Get the code location
     * @return The code location
     */
    @JsonProperty(value = "Location")
    public String getLocation() {
        return this.Location;
    }

    /**
     * Set the code location
     * @param location The code location
     */
    @JsonProperty(value = "Location")
    public void setLocation(String location) {
        this.Location = location;
    }

    /**
     * Get the log storage type
     * @return The log storage type
     */
    @JsonProperty(value = "RepositoryType")
    public String getRepositoryType() {
        return this.RepositoryType;
    }

    /**
     * Set the log storage type
     * @param respositoryType The log storage type
     */
    @JsonProperty(value = "RepositoryType")
    public void setRespositoryType(String respositoryType) {
        this.RepositoryType = respositoryType;
    }

}
