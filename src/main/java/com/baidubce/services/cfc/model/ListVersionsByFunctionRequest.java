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
 * Request object for listing versions of the CFC function
 */
public class ListVersionsByFunctionRequest extends AbstractBceRequest {

    /**
     * Specify the function version. If all functions are not specified to return the $LATEST version, the optional
     * valid value ALL will return all versions, including $LATEST
     */
    @JsonProperty(value = "FunctionName")
    private String FunctionName;

    /**
     * Marker
     */
    @JsonProperty(value = "Marker")
    private Integer Marker;

    /**
     * Max items 1-10000
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
     * Set the function name, you can specify a function name (for example, Thumbnail), Or you can specify the BRN
     * resource name of the function (for example: brn:bce:cfc:bj:account-id:function:thumbnail). CFC also allows you
     * to specify a partial BRN (for example, account-id:Thumbnail). Note that the BRN length is limited to 1-140. If
     * only the function name is specified, the length is limited to 64 characters.
     * @param functionName The function name
     */
    public void setFunctionName(String functionName) {
        this.FunctionName = functionName;
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
    public void setMarker(Integer marker) {
        this.Marker = marker;
    }

    /**
     * get MaxItems 1-10000
     * @return The MaxItems
     */
    @JsonProperty(value = "MaxItems")
    public Integer getMaxItems() {
        return this.MaxItems;
    }

    /**
     * set maxItems 1-10000
     * @param maxItems 1-10000
     */
    public void setMaxItems(Integer maxItems) {
        this.MaxItems = maxItems;
    }

    /**
     * Set the function name
     * @param functionName
     * @return this object
     */
    public ListVersionsByFunctionRequest withFunctionName(String functionName) {
        this.setFunctionName(functionName);
        return this;
    }

    /**
     * Set the function marker
     * @param marker
     * @return this object
     */
    public ListVersionsByFunctionRequest withMarker(Integer marker) {
        this.setMarker(marker);
        return this;
    }

    /**
     * Configure the MaxItems for the request
     *
     * @param maxItems 1-10000
     * @return this object
     */
    public ListVersionsByFunctionRequest withMaxItems(Integer maxItems) {
        this.setMaxItems(maxItems);
        return this;
    }

    /**
     * (non-Javadoc)
     *
     * @see com.baidubce.model.AbstractBceRequest#withRequestCredentials(com.baidubce.auth.BceCredentials)
     */
    @Override
    public ListVersionsByFunctionRequest withRequestCredentials(BceCredentials credentials) {
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

