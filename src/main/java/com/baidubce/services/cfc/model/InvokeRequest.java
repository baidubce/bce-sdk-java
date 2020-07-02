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

import java.util.Map;

/**
 * request object foe invoking a Baidu CFC function
 */
public class InvokeRequest extends AbstractBceRequest {
    /**
     * Function Name You can specify a function name (for example, Thumbnail), or you can specify the function's BRN
     * resource name (for example, brn:bce:cfc:bj:account-id:function:thumbnail:$LATEST). CFC also allows you to
     * specify a partial BRN (for example, account-id:Thumbnail). Note that the BRN length is limited to 1-170. If only
     * the function name is specified, the length is limited to 64 characters.
     */
    @JsonProperty(value = "FunctionName")
    private String FunctionName;

    /**
     * Event (asynchronous call) returns 202/RequestResponse/DryRun. Default RequestResponse
     */
    @JsonProperty(value = "InvocationType")
    private String InvocationType;

    /**
     * Log Type Tail / None You can set this optional parameter to Tail, provided the InvocationType parameter must be
     * RequestResponse. In this example, the CFC returns the last 4KB of base64 encoded log data in the x-bce-log-result header.
     */
    @JsonProperty(value = "LogType")
    private String LogType;

    /**
     * Use this optional parameter to specify a function version or alias. If you specify a function version, the API
     * will use the qualified function BRN to request and return information about the specific CFC function version.
     * If you specify an alias, the API returns information about the version of the function pointed to by the alias.
     * If you don't provide this parameter, the API returns information about the CFC function $LATEST.
     */
    @JsonProperty(value = "Qualifier")
    private String Qualifier;

    /**
     * The input parameters required by the custom function are passed in the body parameter in the json data format.
     * Will call the function as input parameter
     */
    @JsonProperty(value = "Payload")
    private Map<String, String> Payload;

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
     * Get the invocation type
     * @return The invocation type
     */
    @JsonProperty(value = "InvocationType")
    public String getInvocationType() {
        if (this.InvocationType == null) {
            this.InvocationType = "RequestResponse";
        }
        return this.InvocationType;
    }

    /**
     * Set the invocation type
     * @param invocationType The invocation type
     */
    public void setInvocationType(String invocationType) {
        if (invocationType == null) {
            invocationType = "RequestResponse";
        }
        this.InvocationType = invocationType;
    }

    /**
     * Get the log type
     * @return The log type
     */
    @JsonProperty(value = "LogType")
    public String getLogType() {
        return this.LogType;
    }

    /**
     * Set the log type
     * @param logType The log type
     */
    public void setLogType(String logType) {
        this.LogType = logType;
    }

    /**
     * Get the qualifier
     * @return The qualifier
     */
    @JsonProperty(value = "Qualifier")
    public String getQualifier() {
        return this.Qualifier;
    }

    /**
     * Set the qualifier
     * @param qualifier The qualifier
     */
    public void setQualifier(String qualifier) {
        this.Qualifier = qualifier;
    }

    /**
     * Get the payload
     * @return The payload
     */
    @JsonProperty(value = "Payload")
    public Map<String, String> getPayload() {
        return this.Payload;
    }

    /**
     * Set the payload
     * @param payload The payload
     */
    public void setPayload(Map<String, String> payload) {
        this.Payload = payload;
    }

    public InvokeRequest withFunctionName(String functionName) {
        this.setFunctionName(functionName);
        return this;
    }


    public InvokeRequest withInvocationType(String invocationType) {
        this.setInvocationType(invocationType);
        return this;
    }

    public InvokeRequest withLogType(String logType) {
        this.setLogType(logType);
        return this;
    }

    public InvokeRequest withQualifier(String qualifier) {
        this.setQualifier(qualifier);
        return this;
    }

    public InvokeRequest withPayload(Map<String, String> payload) {
        this.setPayload(payload);
        return this;
    }

    public InvokeRequest withRequestCredentials(BceCredentials credentials) {
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

