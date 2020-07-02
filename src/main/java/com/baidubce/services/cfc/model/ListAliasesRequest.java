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
 * Request object for listing aliases for the Baidu CFC function
 */
public class ListAliasesRequest extends AbstractBceRequest {
    /**
     * Function Name The name of the function that created the alias. Note that the length limit only applies to BRN.
     * If only the function name is specified, the length is limited to 64 characters.
     */
    @JsonProperty(value = "FunctionName")
    private String FunctionName;

    /**
     * Function version If you specify this optional parameter, the API only returns an alias pointing to the specific
     * CFC function version, otherwise the API will return all aliases created for the CFC function.
     */
    @JsonProperty(value = "FunctionVersion")
    private String FunctionVersion;

    /**
     * Marker
     */
    @JsonProperty(value = "Marker")
    private Integer Marker;

    /**
     * Max items 10000
     */
    @JsonProperty(value = "MaxItems")
    private Integer MaxItems;

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
    @JsonProperty(value = "FunctionName")
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
    @JsonProperty(value = "FunctionVersion")
    public void setFunctionVersion(String functionVersion) {
        this.FunctionVersion = functionVersion;
    }

    /**
     * Get the marker
     * @return The marker
     */
    @JsonProperty(value = "Marker")
    public Integer getMarker() {
        return this.Marker;
    }

    /**
     * Set the marker
     * @param marker The marker
     */
    @JsonProperty(value = "Marker")
    public void setMarker(Integer marker) {
        this.Marker = marker;
    }

    /**
     * Get the max items
     * @return The max items
     */
    @JsonProperty(value = "MaxItems")
    public Integer getMaxItems() {
        return this.MaxItems;
    }

    /**
     * Set the max items
     * @param maxItems The items
     */
    @JsonProperty(value = "MaxItems")
    public void setMaxItems(Integer maxItems) {
        this.MaxItems = maxItems;
    }

    public ListAliasesRequest withFunctionName(String functionName) {
        this.setFunctionName(functionName);
        return this;
    }

    public ListAliasesRequest withFunctionVersion(String functionVersion) {
        this.setFunctionVersion(functionVersion);
        return this;
    }

    public ListAliasesRequest withMarker(Integer marker) {
        this.setMarker(marker);
        return this;
    }

    public ListAliasesRequest withMaxItems(Integer maxItems) {
        this.setMaxItems(maxItems);
        return this;
    }

    public ListAliasesRequest withRequestCredentials(BceCredentials credentials) {
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

