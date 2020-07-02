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
 * Response object for listing versions of the CFC function
 */
public class ListVersionsByFunctionResponse extends CfcResponse {
    /**
     * Function versions
     */
    @JsonProperty(value = "Versions")
    private List<FunctionConfiguration> Versions = new ArrayList<FunctionConfiguration>();

    /**
     * Get the function version list
     * @return The function version list
     */
    @JsonProperty(value = "Versions")
    public List<FunctionConfiguration> getVersions() {
        return this.Versions;
    }

    /**
     * Set the function version list
     * @param versions The function version list
     */
    public void setVersions(List<FunctionConfiguration> versions) {
        this.Versions = versions;
    }

}

