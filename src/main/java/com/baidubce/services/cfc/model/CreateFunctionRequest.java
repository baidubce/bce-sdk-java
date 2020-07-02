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
 * Request object for creating a Baidu CFC function
 */
public class CreateFunctionRequest extends GenericFunctionRequest {
    /**
     * Function code
     */
    @JsonProperty(value = "Code")
    private Code Code;

    /**
     * Function environment
     */
    @JsonProperty(value = "Environment")
    private Environment Environment;

    /**
     * Entry function of CFC call. For node is module-name.export eg. index.handler maximum length limit is 128.
     */
    @JsonProperty(value = "Handler")
    private String Handler;

    /**
     * Memory size, in megabytes, used by CFC to infer the amount of CPU and memory allocated to user functions.
     * Default 128MB，Must be a multiple of 128MB (now CFC provides 128 to 1024M of memory).
     */
    @JsonProperty(value = "MemorySize")
    private Integer MemorySize;

    /**
     * Run time such as nodejs6.11 nodejs8.5 python2 java8
     */
    @JsonProperty(value = "Runtime")
    private String Runtime;

    /**
     * The timeout for executing a function. 1-300
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
     * Get the function environment
     * @return The function environment
     */
    @JsonProperty(value = "Environment")
    public Environment getEnvironment() {
        return this.Environment;
    }

    /**
     * Set the function environment
     * @param environment The function environment
     */
    public void setEnvironment(Environment environment) {
        this.Environment = environment;
    }

    /**
     * Get the entry function of CFC call.
     * @return The entry function of CFC call
     */
    @JsonProperty(value = "Handler")
    public String getHandler() {
        return this.Handler;
    }

    /**
     * Set the entry function of CFC call. For node is module-name.export eg. index.handler maximum length limit is 128
     * @param handler The entry function of CFC call. For node is module-name.export eg. index.handler maximum length
     *                limit is 128.
     */
    public void setHandler(String handler) {
        this.Handler = handler;
    }

    /**
     * Get the memory siz.
     * @return The memory size
     */
    @JsonProperty(value = "MemorySize")
    public Integer getMemorySize() {
        return this.MemorySize;
    }

    /**
     * Set the memory size. in megabytes, used by CFC to infer the amount of CPU and memory allocated to user functions.
     * Default 128MB，Must be a multiple of 128MB (now CFC provides 128 to 1024M of memory).
     *
     * @param memorySize The memory size
     */
    public void setMemorySize(Integer memorySize) {
        this.MemorySize = memorySize;
    }

    /**
     * Get the runtime for function.
     * @return The runtime for function
     */
    @JsonProperty(value = "Runtime")
    public String getRuntime() {
        return this.Runtime;
    }

    /**
     * Set the runtime for function.
     * @param runtime The runtime for function
     */
    public void setRuntime(String runtime) {
        this.Runtime = runtime;
    }

    /**
     * Get the timeout for executing a function
     * @return The timeout for executing a function
     */
    @JsonProperty(value = "TimeOut")
    public Integer getTimeout() {
        return this.Timeout;
    }

    /**
     * Set the timeout for executing a function. The timeout should be 1-300
     * @param timeout The timeout for executing a function
     */
    public void setTimeout(Integer timeout) {
        this.Timeout = timeout;
    }

    public CreateFunctionRequest(String functionName, String description) {
        super(functionName, description);
    }

    public CreateFunctionRequest withFunctionName(String functionName) {
        this.setFunctionName(functionName);
        return this;
    }

    public CreateFunctionRequest withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public CreateFunctionRequest withCode(Code code) {
        this.setCode(code);
        return this;
    }

    public CreateFunctionRequest withEnvironment(Environment environment) {
        this.setEnvironment(environment);
        return this;
    }

    public CreateFunctionRequest withHandler(String handler) {
        this.setHandler(handler);
        return this;
    }

    public CreateFunctionRequest withMemorySize(Integer memorySize) {
        this.setMemorySize(memorySize);
        return this;
    }

    public CreateFunctionRequest withRuntime(String runtime) {
        this.setRuntime(runtime);
        return this;
    }

    public CreateFunctionRequest withTimeout(Integer timeout) {
        this.setTimeout(timeout);
        return this;
    }

    public CreateFunctionRequest withRequestCredentials(BceCredentials credentials) {
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

