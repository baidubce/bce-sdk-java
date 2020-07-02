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
 * Request object for deleting an alias of Baidu CFC function
 */
public class DeleteAliasRequest extends AbstractBceRequest {
    /**
     * The name of the function which is the alias belong to
     */
    @JsonProperty(value = "FunctionName")
    private String FunctionName;

    /**
     * The name of the alias
     */
    @JsonProperty(value = "AliasName")
    private String AliasName;

    /**
     * Get the function name
     * @return The function name
     */
    @JsonProperty(value = "FunctionName")
    public String getFunctionName() {
        return this.FunctionName;
    }

    /**
     * Set the function name
     * @param functionName The function name
     */
    public void setFunctionName(String functionName) {
        this.FunctionName = functionName;
    }

    /**
     * Get the alias name
     * @return The alias name
     */
    @JsonProperty(value = "AliasName")
    public String getAliasName() {
        return this.AliasName;
    }

    /**
     * Set the alias name
     * @param aliasName The alias name
     */
    public void setAliasName(String aliasName) {
        this.AliasName = aliasName;
    }

    public DeleteAliasRequest withFunctionName(String functionName) {
        this.setFunctionName(functionName);
        return this;
    }

    public DeleteAliasRequest withAliasName(String aliasName) {
        this.setAliasName(aliasName);
        return this;
    }

    public DeleteAliasRequest withRequestCredentials(BceCredentials credentials) {
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
