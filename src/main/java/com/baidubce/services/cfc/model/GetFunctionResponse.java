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
 * Response object for getting a Baidu CFC function
 */
public class GetFunctionResponse extends CfcResponse {
    /**
     * Function code
     */
    @JsonProperty(value = "Code")
    private CodeStorage Code;

    /**
     * Function information
     */
    @JsonProperty(value = "Configuration")
    private FunctionConfiguration Configuration;

    /**
     * Get function code
     * @return The function code
     */
    @JsonProperty(value = "Code")
    public CodeStorage getCode() {
        return this.Code;
    }

    /**
     * Set the function code
     * @param code The function code
     */
    @JsonProperty(value = "Code")
    public void setCode(CodeStorage code) {
        this.Code = code;
    }

    /**
     * Get the function information
     * @return The function information
     */
    @JsonProperty(value = "Configuration")
    public FunctionConfiguration getConfiguration() {
        return this.Configuration;
    }

    /**
     * Set the function information
     * @param configuration The function information
     */
    @JsonProperty(value = "Configuration")
    public void setConfiguration(FunctionConfiguration configuration) {
        this.Configuration = configuration;
    }
}

