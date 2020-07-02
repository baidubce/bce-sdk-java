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
 * Request object for listing the CFC functions
 */
public class ListFunctionsRequest extends AbstractBceRequest {
    /**
     * Specify the function version. If all functions are not specified to return the $LATEST version, the optional
     * valid value ALL will return all versions, including $LATEST
     */
    @JsonProperty(value = "FunctionVersion")
    private String FunctionVersion;

    /**
     * Marker
     */
    @JsonProperty(value = "Marker")
    private Integer Marker;

    /**
     * 1-10000
     */
    @JsonProperty(value = "MaxItems")
    private Integer MaxItems;

    /**
     * Get function version. If all functions are not specified to return the $LATEST version, the optional
     * valid value ALL will return all versions, including $LATEST
     * @return
     */
    @JsonProperty(value = "FunctionVersion")
    public String getFunctionVersion() {
        return this.FunctionVersion;
    }

    /**
     * Set function version. If all functions are not specified to return the $LATEST version, the optional
     * valid value ALL will return all versions, including $LATEST
     * @param functionVersion
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
     * get MaxItems 1-10000
     * @return
     */
    @JsonProperty(value = "MaxItems")
    public Integer getMaxItems() {
        return this.MaxItems;
    }

    /**
     * set maxItems 1-10000
     * @param maxItems 1-10000
     */
    @JsonProperty(value = "MaxItems")
    public void setMaxItems(Integer maxItems) {
        this.MaxItems = maxItems;
    }

    /**
     * Specify the function version. If all functions are not specified to return the $LATEST version, the optional
     * valid value ALL will return all versions, including $LATEST
     * @param functionVersion
     * @return this object
     */
    public ListFunctionsRequest withFunctionVersion(String functionVersion) {
        this.setFunctionVersion(functionVersion);
        return this;
    }

    /**
     * Specify the function marker
     * @param marker
     * @return this object
     */
    public ListFunctionsRequest withMarker(Integer marker) {
        this.setMarker(marker);
        return this;
    }

    /**
     * Configure the MaxItems for the request
     *
     * @param maxItems 1-10000
     * @return this object
     */
    public ListFunctionsRequest withMaxItems(Integer maxItems) {
        this.setMaxItems(maxItems);
        return this;
    }

    /**
     * (non-Javadoc)
     *
     * @see com.baidubce.model.AbstractBceRequest#withRequestCredentials(com.baidubce.auth.BceCredentials)
     */
    @Override
    public ListFunctionsRequest withRequestCredentials(BceCredentials credentials) {
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

