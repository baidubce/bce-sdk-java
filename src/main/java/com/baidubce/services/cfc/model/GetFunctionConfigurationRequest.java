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
 * Request object for getting Baidu CFC function configuration
 */
public class GetFunctionConfigurationRequest extends AbstractBceRequest {
    /**
     * Function Name You can specify a function name (for example, Thumbnail), or you can specify the function's BRN
     * resource name (for example, brn:bce:cfc:bj:account-id:function:thumbnail:$LATEST). CFC also allows you to
     * specify a partial BRN (for example, account-id:Thumbnail). Note that the BRN length is limited to 1-170. If only
     * the function name is specified, the length is limited to 64 characters.
     */
    @JsonProperty(value = "FunctionName")
    private String FunctionName;

    /**
     * Use this optional parameter to specify a function version or alias. If you specify a function version, the API
     * will use the qualified function BRN to request and return information about the specific CFC function version.
     * If you specify an alias, the API returns information about the version of the function pointed to by the alias.
     * If you don't provide this parameter, the API returns information about the CFC function $LATEST.
     */
    @JsonProperty(value = "Qualifier")
    private String Qualifier;

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
     * Get the function qualifier
     * @return The function qualifier
     */
    @JsonProperty(value = "Qualifier")
    public String getQualifier() {
        return this.Qualifier;
    }

    /**
     * Set the function qualifier
     * @param qualifier The function qualifier
     */
    public void setQualifier(String qualifier) {
        this.Qualifier = qualifier;
    }

    public GetFunctionConfigurationRequest withFunctionName(String functionName) {
        this.setFunctionName(functionName);
        return this;
    }

    public GetFunctionConfigurationRequest withQualifier(String qualifier) {
        this.setQualifier(qualifier);
        return this;
    }

    /**
     * (non-Javadoc)
     *
     * @see com.baidubce.model.AbstractBceRequest#withRequestCredentials(com.baidubce.auth.BceCredentials)
     */
    @Override
    public GetFunctionConfigurationRequest withRequestCredentials(BceCredentials credentials) {
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

