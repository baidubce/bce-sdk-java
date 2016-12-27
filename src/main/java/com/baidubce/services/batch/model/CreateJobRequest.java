/*
 * Copyright 2016 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.batch.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Provides options for creating a Batch-Compute job.
 */
public class CreateJobRequest extends AbstractBceRequest {
    private String clientToken;
    private String name;
    private String vmType;
    private int vmCount;
    private String jobDagJson;
    private Integer jobTimeoutInSeconds;
    private String memo;

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVmType() {
        return vmType;
    }

    public void setVmType(String vmType) {
        this.vmType = vmType;
    }

    public int getVmCount() {
        return vmCount;
    }

    public void setVmCount(int vmCount) {
        this.vmCount = vmCount;
    }

    public String getJobDagJson() {
        return jobDagJson;
    }

    public void setJobDagJson(String jobDagJson) {
        this.jobDagJson = jobDagJson;
    }

    public Integer getJobTimeoutInSeconds() {
        return jobTimeoutInSeconds;
    }

    public void setJobTimeoutInSeconds(Integer jobTimeoutInSeconds) {
        this.jobTimeoutInSeconds = jobTimeoutInSeconds;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * Configure optional client token for the request. The request will be idempotent if client token is provided.
     *
     * @param clientToken An ASCII string whose length is less than 64.
     * @return CreateJobRequest
     */
    public CreateJobRequest withClientToken(String clientToken) {
        this.setClientToken(clientToken);
        return this;
    }

    public CreateJobRequest withName(String name) {
        this.setName(name);
        return this;
    }

    public CreateJobRequest withVmType(String vmType) {
        this.setVmType(vmType);
        return this;
    }

    public CreateJobRequest withVmCount(int vmCount) {
        this.setVmCount(vmCount);
        return this;
    }

    public CreateJobRequest withJobDagJson(String jobDagJson) {
        this.setJobDagJson(jobDagJson);
        return this;
    }

    public CreateJobRequest withJobTimeoutInSeconds(Integer jobTimeoutInSeconds) {
        this.setJobTimeoutInSeconds(jobTimeoutInSeconds);
        return this;
    }

    public CreateJobRequest withMemo(String memo) {
        this.setMemo(memo);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return CreateJobRequest
     */
    public CreateJobRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
