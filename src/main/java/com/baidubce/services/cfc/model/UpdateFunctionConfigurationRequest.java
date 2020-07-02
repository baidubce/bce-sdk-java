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
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Request object for updating the CFC function configuration
 */
public class UpdateFunctionConfigurationRequest extends GenericFunctionRequest {
    /**
     * Function code
     */
    @JsonProperty(value = "Code")
    private Code Code;

    /**
     * Environment
     */
    @JsonProperty(value = "Environment")
    private Environment Environment;

    /**
     * The entry function of the CFC call, for the module is module-name.export eg. index.handler maximum length limit
     * is 128
     */
    @JsonProperty(value = "Handler")
    private String Handler;

    /**
     * The size of the memory, in megabytes, that CFC uses to estimate the amount of CPU and memory allocated to the
     * user function. The default value is 128MB, which must be a multiple of 128MB (now CFC provides 128 to 1024M of
     * memory)
     */
    @JsonProperty(value = "MemorySize")
    private Integer MemorySize;

    /**
     * Runtime nodejs6.11 nodejs8.5 python2 java8
     */
    @JsonProperty(value = "Runtime")
    private String Runtime;

    /**
     * Timeout 1-300，max timeout is 300
     */
    @JsonProperty(value = "Timeout")
    private Integer Timeout;

    /**
     * Get the function code
     * @return The function code
     */
    @JsonProperty(value = "Code")
    public Code getCode() {
        return this.Code;
    }

    /**
     * Set the function code
     * @param code The function code
     */
    public void setCode(Code code) {
        this.Code = code;
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
     * Get the entry function of the CFC call, for the module is module-name.export eg. index.handler maximum length
     * limit is 128
     * @return
     */
    @JsonProperty(value = "Handler")
    public String getHandler() {
        return this.Handler;
    }

    /**
     * Set the entry function of the CFC call, for the module is module-name.export eg. index.handler maximum length
     * limit is 128
     *
     * @param handler The entry function of the CFC call, for the module is module-name.export eg. index.handler
     *                maximum length limit is 128
     */
    public void setHandler(String handler) {
        this.Handler = handler;
    }

    /**
     * Get the size of the memory, in megabytes, that CFC uses to estimate the amount of CPU and memory allocated to
     * the user function. The default value is 128MB, which must be a multiple of 128MB (now CFC provides 128 to 1024M
     * of memory)
     * @return
     */
    @JsonProperty(value = "MemorySize")
    public Integer getMemorySize() {
        return this.MemorySize;
    }

    /**
     * Set the size of the memory, in megabytes, that CFC uses to estimate the amount of CPU and memory allocated to
     * the user function. The default value is 128MB, which must be a multiple of 128MB (now CFC provides 128 to 1024M
     * of memory)
     * @param memorySize The size of the memory, in megabytes, that CFC uses to estimate the amount of CPU and memory
     *                   allocated to the user function. The default value is 128MB, which must be a multiple of 128MB
     *                   (now CFC provides 128 to 1024M of memory)
     */
    public void setMemorySize(Integer memorySize) {
        this.MemorySize = memorySize;
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
     * Get the timeout
     * @return The timeout
     */
    @JsonProperty(value = "Timeout")
    public Integer getTimeout() {
        return this.Timeout;
    }

    /**
     * Set the timeout
     * @param timeout The timeout
     */
    public void setTimeout(Integer timeout) {
        this.Timeout = timeout;
    }

    public UpdateFunctionConfigurationRequest(String functionName, String description) {

        super(functionName, description);
    }

    public UpdateFunctionConfigurationRequest withFunctionName(String functionName) {
        this.setFunctionName(functionName);
        return this;
    }

    public UpdateFunctionConfigurationRequest withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public UpdateFunctionConfigurationRequest withCode(Code code) {
        this.setCode(code);
        return this;
    }

    public UpdateFunctionConfigurationRequest withEnvironment(Environment environment) {
        this.setEnvironment(environment);
        return this;
    }

    public UpdateFunctionConfigurationRequest withHandler(String handler) {
        this.setHandler(handler);
        return this;
    }

    public UpdateFunctionConfigurationRequest withMemorySize(Integer memorySize) {
        this.setMemorySize(memorySize);
        return this;
    }

    public UpdateFunctionConfigurationRequest withRuntime(String runtime) {
        this.setRuntime(runtime);
        return this;
    }

    public UpdateFunctionConfigurationRequest withTimeout(Integer timeout) {
        this.setTimeout(timeout);
        return this;
    }

    public UpdateFunctionConfigurationRequest withRequestCredentials(BceCredentials credentials) {
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

