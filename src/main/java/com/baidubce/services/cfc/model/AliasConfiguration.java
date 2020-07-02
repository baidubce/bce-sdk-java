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
 * Represent the basic alias information about a CFC function. The information contains the basic information of the
 * alias, such as function_name, name, description, etc.
 */
public class AliasConfiguration extends JsonObject {

    /**
     * The baidu resource name for alias
     */
    @JsonProperty(value = "AliasBrn")
    private String AliasBrn;

    /**
     * Compatible with AWS Lambda amazon resource name. Same as AliasBrn
     */
    @JsonProperty(value = "AliasArn")
    private String AliasArn;

    /**
     * The name of the function, consisting of a number, a letter,  - or _. Length limited to 64 characters
     */
    @JsonProperty(value = "FunctionName")
    private String FunctionName;

    /**
     * The version of the function.$LATEST means the latest, otherwise it consists of numbers and length limited 1-32
     * characters
     */
    @JsonProperty(value = "FunctionVersion")
    private String FunctionVersion;

    /**
     * The name of the alias
     */
    @JsonProperty(value = "Name")
    private String Name;

    /**
     * A short description of the alias. Length limited 0-256 characters.
     */
    @JsonProperty(value = "Description")
    private String Description;

    /**
     * User ID (consists of numbers, letters and _), length limited 128 characters
     */
    @JsonProperty(value = "Uid")
    private String Uid;

    /**
     * The alias latest update time. ISO-8601 format (YYYY-MM-DDThh:mm:ss.sTZD)
     */
    @JsonProperty(value = "UpdatedAt")
    private String UpdatedAt;

    /**
     * Represent the alias create time. ISO-8601 format (YYYY-MM-DDThh:mm:ss.sTZD)
     */
    @JsonProperty(value = "CreatedAt")
    private String CreatedAt;

    /**
     * Get the AliasBrn for alias
     * @return The AliasBrn for alias
     */
    @JsonProperty(value = "AliasBrn")
    public String getAliasBrn() {
        return this.AliasBrn;
    }

    /**
     * Set the AliasBrn for alias
     * @param aliasBrn The AliasBrn for alias
     */
    @JsonProperty(value = "AliasBrn")
    public void setAliasBrn(String aliasBrn) {
        this.AliasBrn = aliasBrn;
    }

    /**
     * Get the AliasArn for alias
     * @return The AliasArn for alias
     */
    @JsonProperty(value = "AliasArn")
    public String getAliasArn() {
        return this.AliasArn;
    }

    /**
     * Set the AliasArn for alias
     * @param aliasArn The AliasArn for alias
     */
    @JsonProperty(value = "AliasArn")
    public void setAliasArn(String aliasArn) {
        this.AliasArn = aliasArn;
    }

    /**
     * Get the FunctionName for function
     * @return The FunctionName for function
     */
    @JsonProperty(value = "FunctionName")
    public String getFunctionName() {
        return this.FunctionName;
    }

    /**
     * Set the FunctionName for function
     * @param functionName The FunctionName for function
     */
    @JsonProperty(value = "FunctionName")
    public void setFunctionName(String functionName) {
        this.FunctionName = functionName;
    }

    /**
     * Get the FunctionVersion for function
     * @return The FunctionVersion for function
     */
    @JsonProperty(value = "FunctionVersion")
    public String getFunctionVersion() {
        return this.FunctionVersion;
    }

    /**
     * Set the FunctionVersion for function
     * @param functionVersion The FunctionVersion for function
     */
    @JsonProperty(value = "FunctionVersion")
    public void setFunctionVersion(String functionVersion) {
        this.FunctionVersion = functionVersion;
    }

    /**
     * Get the Name for alias
     * @return The Name for alias
     */
    @JsonProperty(value = "Name")
    public String getName() {
        return this.Name;
    }

    /**
     * Set the Name for alias
     * @param name The Name for alias
     */
    @JsonProperty(value = "Name")
    public void setName(String name) {
        this.Name = name;
    }

    /**
     * Get the Description for alias
     * @return The Description for alias
     */
    @JsonProperty(value = "Description")
    public String getDescription() {
        return this.Description;
    }

    /**
     * Set the Description for alias
     * @param description The Description for alias
     */
    @JsonProperty(value = "Description")
    public void setDescription(String description) {
        this.Description = description;
    }

    /**
     * Get the Uid for the function user
     * @return The Uid for the function user
     */
    @JsonProperty(value = "Uid")
    public String getUid() {
        return this.Uid;
    }

    /**
     * Set the Uid for the function user
     * @param uid The Uid for the function user
     */
    @JsonProperty(value = "Uid")
    public void setUid(String uid) {
        this.Uid = uid;
    }

    /**
     * Get the UpdatedAt for alias
     * @return The UpdatedAt for alias
     */
    @JsonProperty(value = "UpdatedAt")
    public String getUpdatedAt() {
        return this.UpdatedAt;
    }

    /**
     * Set the UpdatedAt for alias
     * @param updatedAt The UpdatedAt for alias
     */
    @JsonProperty(value = "UpdatedAt")
    public void setUpdatedAt(String updatedAt) {
        this.UpdatedAt = updatedAt;
    }

    /**
     * Get the CreatedAt for alias
     * @return The CreatedAt for alias
     */
    @JsonProperty(value = "CreatedAt")
    public String getCreatedAt() {
        return this.CreatedAt;
    }

    /**
     * Set the CreatedAt for alias
     * @param createdAt The CreatedAt for alias
     */
    @JsonProperty(value = "CreatedAt")
    public void setCreatedAt(String createdAt) {
        this.CreatedAt = createdAt;
    }

}
