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
 * Request object for listing triggers for the CFC function
 */
public class ListTriggersRequest extends AbstractBceRequest {
    /**
     * Function BRN
     */
    @JsonProperty(value = "FunctionBrn")
    private String FunctionBrn;

    /**
     * Get the function BRN
     * @return The function BRN
     */
    @JsonProperty(value = "FunctionBrn")
    public String getFunctionBrn() {
        return this.FunctionBrn;
    }

    /**
     * Set the function BRN
     * @param functionBrn The function BRN
     */
    public void setFunctionBrn(String functionBrn) {
        this.FunctionBrn = functionBrn;
    }

    public ListTriggersRequest withFunctionBrn(String functionBrn) {
        this.setFunctionBrn(functionBrn);
        return this;
    }

    public ListTriggersRequest withRequestCredentials(BceCredentials credentials) {
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

