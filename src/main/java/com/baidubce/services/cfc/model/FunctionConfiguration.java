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
 * Represent the basic information of the Baidu CFC function
 */
public class FunctionConfiguration extends JsonObject {

    /**
     * User ID (consisting of numbers, letters, underscores), length limited to 128 characters
     */
    @JsonProperty(value = "Uid")
    private String Uid;

    /**
     * Function description, short description 0-256 characters
     */
    @JsonProperty(value = "Description")
    private String Description;

    /**
     * The unique resource identifier of the function, 1-170 characters
     */
    @JsonProperty(value = "FunctionBrn")
    private String FunctionBrn;

    /**
     * Regional, currently there are bj (Beijing), su (Suzhou), gz (Guangzhou)
     */
    @JsonProperty(value = "Region")
    private String Region;

    /**
     * Timeout, 1-300, maximum 300
     */
    @JsonProperty(value = "Timeout")
    private Integer Timeout;

    /**
     * Version description, 0-256 characters
     */
    @JsonProperty(value = "VersionDesc")
    private String VersionDesc;

    /**
     * The latest update time of the function, ISO-8601 format (YYYY-MM-DDThh:mm:ss.sTZD)
     */
    @JsonProperty("UpdatedAt")
    private String UpdatedAt;

    /**
     * Compatible with AWS Lambda with UpdatedAt
     */
    @JsonProperty(value = "LastModified")
    private String LastModified;

    /**
     * SHA256 hash of function code package
     */
    @JsonProperty(value = "CodeSha256")
    private String CodeSha256;

    /**
     * The size of the function package, in bytes
     */
    @JsonProperty(value = "CodeSize")
    private Integer CodeSize;

    /**
     * Compatible with AWS Lambda with FunctionBrn
     */
    @JsonProperty("FunctionArn")
    private String FunctionArn;

    /**
     * Function name, consisting of numbers, letters, - or _, limited to 64 characters in length
     */
    @JsonProperty(value = "FunctionName")
    private String FunctionName;

    /**
     * The entry function of the CFC call, for the module is module-name.export eg. index.handler maximum length
     * is 128 characters
     */
    @JsonProperty(value = "Handler")
    private String Handler;

    /**
     * Version, $LATEST means the latest, otherwise it consists of numbers, 1-32 characters
     */
    @JsonProperty(value = "Version")
    private String Version;

    /**
     * Runtime nodejs6.11、nodejs8.5、python2、java8
     */
    @JsonProperty(value = "Runtime")
    private String Runtime;

    /**
     * Memory size, in megabytes, used by CFC to infer the amount of CPU and memory allocated to user functions.
     * Default 128MB，Must be a multiple of 128MB (now CFC provides 128 to 1024M of memory).
     */
    @JsonProperty(value = "MemorySize")
    private Integer MemorySize;

    /**
     * Environment
     */
    @JsonProperty(value = "Environment")
    private Environment Environment;

    /**
     * CommitId
     */
    @JsonProperty(value = "CommitId")
    private String CommitId;

    /**
     * Function execution role
     */
    @JsonProperty("Role")
    private String Role;

    /**
     * Log type bos（baidu object store） / none
     */
    @JsonProperty(value = "LogType")
    private String LogType;

    /**
     * the Bucket location of the log
     */
    @JsonProperty(value = "LogBosDir")
    private String LogBosDir;

    /**
     * Get the user ID
     * @return The user ID
     */
    @JsonProperty(value = "Uid")
    public String getUid() {
        return this.Uid;
    }

    /**
     * Set the user ID (consisting of numbers, letters, underscores), length limited to 128 characters
     * @param uid The user ID (consisting of numbers, letters, underscores), length limited to 128 characters
     */
    public void setUid(String uid) {
        this.Uid = uid;
    }

    /**
     * Get the function description
     * @return The function description
     */
    @JsonProperty(value = "Description")
    public String getDescription() {
        return this.Description;
    }

    /**
     * Set the function description，0-256 characters
     * @param description The function description，0-256 characters
     */
    public void setDescription(String description) {
        this.Description = description;
    }

    /**
     * Get the BRN (Baidu resource name) of the function
     * @return The BRN (Baidu resource name) of the function
     */
    @JsonProperty(value = "FunctionBrn")
    public String getFunctionBrn() {
        return this.FunctionBrn;
    }

    /**
     * Set the BRN (Baidu resource name) of the function. 1-170 characters
     * @param functionBrn The BRN (Baidu resource name) of the function. 1-170 characters
     */
    public void setFunctionBrn(String functionBrn) {
        this.FunctionBrn = functionBrn;
    }

    /**
     * Get the region which function belong to
     * @return The region which function belong to
     */
    @JsonProperty(value = "Region")
    public String getRegion() {
        return this.Region;
    }

    /**
     * Set the region which function belong to. The current regions include bj（beijing）、su（suzhou）、gz
     * （guangzhou)
     * @param region The region which function belong to
     */
    public void setRegion(String region) {
        this.Region = region;
    }

    /**
     * Get the timeout
     * @return The timeout
     */
    @JsonProperty(value = "Timeout")
    public Integer getTimeout() {
        return this.Timeout;
    }

    /**
     * Set the timeout, 1-300
     * @param timeout The timeout
     */
    public void setTimeout(Integer timeout) {
        this.Timeout = timeout;
    }

    /**
     * Get the function description
     * @return The function description
     */
    @JsonProperty(value = "VersionDesc")
    public String getVersionDesc() {
        return this.VersionDesc;
    }

    /**
     * Set the function description. 0-256 characters
     * @param versionDesc The function description
     */
    public void setVersionDesc(String versionDesc) {
        this.VersionDesc = versionDesc;
    }

    /**
     * Get the update time of the function. ISO-8601 format (YYYY-MM-DDThh:mm:ss.sTZD)
     * @return The update time of the function. ISO-8601 format (YYYY-MM-DDThh:mm:ss.sTZD)
     */
    @JsonProperty(value = "UpdatedAt")
    public String getUpdatedAt() {
        return this.UpdatedAt;
    }

    /**
     * Set the update time of the function. ISO-8601 format (YYYY-MM-DDThh:mm:ss.sTZD)
     * @param updatedAt The update time of the function. ISO-8601 format (YYYY-MM-DDThh:mm:ss.sTZD)
     */
    public void setUpdatedAt(String updatedAt) {
        this.UpdatedAt = updatedAt;
    }

    /**
     * Get the last modified time which is same as UpdateAt. Compatible with AWS Lambda
     * @return The last modified time which is same as UpdateAt. Compatible with AWS Lambda
     */
    @JsonProperty(value = "LastModified")
    public String getLastModified() {
        return this.LastModified;
    }

    /**
     * Set the last modified time which is same as UpdateAt. Compatible with AWS Lambda
     * @param lastModified The last modified time which is same as UpdateAt. Compatible with AWS Lambda
     */
    public void setLastModified(String lastModified) {
        this.LastModified = lastModified;
    }

    /**
     * Get the SHA256 hash of function code package
     * @returnm The SHA256 hash of function code package
     */
    @JsonProperty(value = "CodeSha256")
    public String getCodeSha256() {
        return this.CodeSha256;
    }

    /**
     * Set the SHA256 hash of function code package
     * @param codeSha256 The SHA256 hash of function code package
     */
    public void setCodeSha256(String codeSha256) {
        this.CodeSha256 = codeSha256;
    }

    /**
     * Get the size of the function package, in bytes
     * @return The size of the function package, in bytes
     */
    @JsonProperty(value = "CodeSize")
    public Integer getCodeSize() {
        return this.CodeSize;
    }

    /**
     * Set the size of the function package, in bytes
     * @param codeSize The size of the function package, in bytes
     */
    public void setCodeSize(Integer codeSize) {
        this.CodeSize = codeSize;
    }

    /**
     * Get the FunctionArn. Compatible with AWS Lambda with FunctionBrn
     * @return The FunctionArn. Compatible with AWS Lambda with FunctionBrn
     */
    @JsonProperty(value = "FunctionArn")
    public String getFunctionArn() {
        return this.FunctionArn;
    }

    /**
     * Set the FunctionArn. Compatible with AWS Lambda with FunctionBrn
     * @param functionArn The FunctionArn. Compatible with AWS Lambda with FunctionBrn
     */
    public void setFunctionArn(String functionArn) {
        this.FunctionArn = functionArn;
    }

    /**
     * Get the function name, consisting of numbers, letters, - or _, limited to 64 characters in length
     * @return The function name, consisting of numbers, letters, - or _, limited to 64 characters in length
     */
    @JsonProperty(value = "FunctionName")
    public String getFunctionName() {
        return this.FunctionName;
    }

    /**
     * Set the function name, consisting of numbers, letters, - or _, limited to 64 characters in length
     * @param functionName The function name, consisting of numbers, letters, - or _, limited to 64 characters in
     *                     length
     */
    public void setFunctionName(String functionName) {
        this.FunctionName = functionName;
    }

    /**
     * Get the entry function of the CFC call, for the module is module-name.export eg. index.handler maximum
     * length is 128 characters
     * @return The entry function of the CFC call, for the module is module-name.export eg. index.handler maximum
     * length is 128 characters
     */
    @JsonProperty(value = "Handler")
    public String getHandler() {
        return this.Handler;
    }

    /**
     * Set the entry function of the CFC call, for the module is module-name.export eg. index.handler maximum
     * length is 128 characters
     * @param handler The entry function of the CFC call, for the module is module-name.export eg. index.handler
     *                maximum length is 128 characters
     */
    public void setHandler(String handler) {
        this.Handler = handler;
    }

    /**
     * Get the version, $LATEST means the latest, otherwise it consists of numbers, 1-32 characters
     * @return The version, $LATEST means the latest, otherwise it consists of numbers, 1-32 characters
     */
    @JsonProperty(value = "Version")
    public String getVersion() {
        return this.Version;
    }

    /**
     * Set the version, $LATEST means the latest, otherwise it consists of numbers, 1-32 characters
     * @param version The version, $LATEST means the latest, otherwise it consists of numbers, 1-32 characters
     */
    public void setVersion(String version) {
        this.Version = version;
    }

    /**
     * Get the runtime
     * @return The runtime
     */
    @JsonProperty(value = "Runtime")
    public String getRuntime() {
        return this.Runtime;
    }

    /**
     * Set the runtime
     * @param runtime The runtime
     */
    public void setRuntime(String runtime) {
        this.Runtime = runtime;
    }

    /**
     * Get the memory size
     * @return The memory size
     */
    @JsonProperty(value = "MemorySize")
    public Integer getMemorySize() {
        return this.MemorySize;
    }

    /**
     * Set the memory size, in megabytes, used by CFC to infer the amount of CPU and memory allocated to user
     * functions. Default 128MB，Must be a multiple of 128MB (now CFC provides 128 to 1024M of memory).
     * @param memorySize The memory size, in megabytes, used by CFC to infer the amount of CPU and memory
     *                   allocated to user functions. Default 128MB，Must be a multiple of 128MB (now CFC provides 128
     *                   to 1024M of memory).
     */
    public void setMemorySize(Integer memorySize) {
        this.MemorySize = memorySize;
    }

    /**
     * Get the environment
     * @return The environment
     */
    @JsonProperty(value = "Environment")
    public Environment getEnvironment() {
        return this.Environment;
    }

    /**
     * Set the environment
     * @param environment The environment
     */
    public void setEnvironment(Environment environment) {
        this.Environment = environment;
    }

    /**
     * Get the commitId
     * @return The commitId
     */
    @JsonProperty(value = "CommitId")
    public String getCommitId() {
        return this.CommitId;
    }

    /**
     * Set the commitId
     * @param commitId The commitId
     */
    public void setCommitId(String commitId) {
        this.CommitId = commitId;
    }

    /**
     * Get the function execution role
     * @return The function execution role
     */
    @JsonProperty(value = "Role")
    public String getRole() {
        return this.Role;
    }

    /**
     * Set the function execution role
     * @param role The function execution role
     */
    public void setRole(String role) {
        this.Role = role;
    }

    /**
     * Get the log type bos /none
     * @return The log type
     */
    @JsonProperty(value = "LogType")
    public String getLogType() {
        return this.LogType;
    }

    /**
     * Set the log type bos /none
     * @param logType The log type bos /none
     */
    public void setLogType(String logType) {
        this.LogType = logType;
    }

    /**
     * Get the log stored bucket address
     * @return The log stored bucket address
     */
    @JsonProperty(value = "LogBodDir")
    public String getLogBosDir() {
        return this.LogBosDir;
    }

    /**
     * Set the log stored bucket address
     * @param logBosDir The log stored bucket address
     */
    public void setLogBosDir(String logBosDir) {
        this.LogBosDir = logBosDir;
    }
}

