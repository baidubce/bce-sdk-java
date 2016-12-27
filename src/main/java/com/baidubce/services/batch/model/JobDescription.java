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

/**
 * Represent a Batch-Compute JobDescription.
 */
public class JobDescription {
    private String name;
    private String vmType;
    private int vmCount;
    private String jobDagJson;
    private Long jobTimeoutInSeconds;
    private String memo;

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

    public Long getJobTimeoutInSeconds() {
        return jobTimeoutInSeconds;
    }

    public void setJobTimeoutInSeconds(Long jobTimeoutInSeconds) {
        this.jobTimeoutInSeconds = jobTimeoutInSeconds;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
