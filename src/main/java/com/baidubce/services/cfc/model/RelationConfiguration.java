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
 * Represent the basic information of the trigger relation
 */
public class RelationConfiguration {
    /**
     * unique identifier resource of the trigger
     */
    @JsonProperty(value = "Brn")
    private String Brn;

    /**
     * Authentication type, optional values are "anonymous" or "iam"
     */
    @JsonProperty(value = "AuthType")
    private String AuthType;

    /**
     * Resource domain name prefix
     */
    @JsonProperty(value = "EndpointPrefix")
    private String EndpointPrefix;

    /**
     * HTTP method,  such as "GET,HEAD"
     */
    @JsonProperty(value = "Method")
    private String Method;

    /**
     * URL
     */
    @JsonProperty(value = "ResourcePath")
    private String ResourcePath;

    /**
     * Get the Brn
     * @return The Brn
     */
    @JsonProperty(value = "Brn")
    public String getBrn() {
        return this.Brn;
    }

    /**
     * Set the Brn
     * @param brn The Brn
     */
    public void setBrn(String brn) {
        this.Brn = brn;
    }

    /**
     * Get the auth type
     * @return The auth type
     */
    @JsonProperty(value = "AuthType")
    public String getAuthType() {
        return this.AuthType;
    }

    /**
     * Set the auth type
     * @param authType The auth type
     */
    public void setAuthType(String authType) {
        this.AuthType = authType;
    }

    /**
     * Get the end point prefix
     * @return The end point prefix
     */
    @JsonProperty(value = "EndpointPrefix")
    public String getEndpointPrefix() {
        return this.EndpointPrefix;
    }

    /**
     * Set the end point prefix
     * @param endpointPrefix The end point prefix
     */
    public void setEndpointPrefix(String endpointPrefix) {
        this.EndpointPrefix = endpointPrefix;
    }

    /**
     * Get the HTTP method
     * @return The HTTP method
     */
    @JsonProperty(value = "Method")
    public String getMethod() {
        return this.Method;
    }

    /**
     * Set the HTTP method
     * @param method The HTTP method
     */
    public void setMethod(String method) {
        this.Method = method;
    }

    /**
     * Get the resource path
     * @return The resource path
     */
    @JsonProperty(value = "ResourcePath")
    public String getResourcePath() {
        return this.ResourcePath;
    }

    /**
     * Set the resource path
     * @param resourcePsth The resource path
     */
    public void setResourcePath(String resourcePsth) {
        this.ResourcePath = resourcePsth;
    }
}

