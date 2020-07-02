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
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Request object for creating a alias for Baidu CFC function.
 */
public class CreateAliasRequest extends GenericAliasRequest {

    /**
     * The function name. Note that the length limit only applies to BRN. If only the function name is specified, the
     * length is limited to 64 characters.
     */
    @JsonProperty(value = "FunctionName")
    private String FunctionName;
    /**
     * Alias points to the function version ($LATEST|[0-9]+)
     */
    @JsonProperty(value = "FunctionVersion")
    private String FunctionVersion;

    /**
     * The description of alias
     */
    @JsonProperty(value = "Description")
    private String Description;

    /**
     * Get the function name
     * @return The function name
     */
    @JsonProperty(value = "FunctionName")
    public String getFunctionName() {
        return this.FunctionName;
    }

    /**
     * Set the function name. Note that the length limit only applies to BRN. If only the function name is specified,
     * the length is limited to 64 characters.
     * @param functionName
     */
    public void setFunctionName(String functionName) {
        this.FunctionName = functionName;
    }

    /**
     * Get the function version
     * @return The function version
     */
    @JsonProperty(value = "FunctionVersion")
    public String getFunctionVersion() {
        return this.FunctionVersion;
    }

    /**
     * Set the function version
     * @param functionVersion The function version
     */
    public void setFunctionVersion(String functionVersion) {
        this.FunctionVersion = functionVersion;
    }

    /**
     * Get the description of the alias
     * @return The description of alias
     */
    @JsonProperty(value = "Description")
    public String getDescription() {
        return this.Description;
    }

    /**
     * Set the description of alias
     * @param description The description of alias
     */
    public void setDescription(String description) {
        this.Description = description;
    }

    public CreateAliasRequest(String name) {
        super(name);
    }

    public CreateAliasRequest withName(String name) {
        this.setName(name);
        return this;
    }

    public CreateAliasRequest withFunctionName(String functionName) {
        this.setFunctionName(functionName);
        return this;
    }

    public CreateAliasRequest withFunctionVersion(String functionVersion) {
        this.setFunctionVersion(functionVersion);
        return this;
    }

    public CreateAliasRequest withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public CreateAliasRequest withRequestCredentials(BceCredentials credentials) {
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
