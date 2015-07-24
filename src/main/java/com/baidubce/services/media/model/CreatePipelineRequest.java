/*
 * Copyright (c) 2015 Baidu.com, Inc. All Rights Reserved
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
 
package com.baidubce.services.media.model;

import static com.baidubce.util.Validate.checkStringNotEmpty;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class CreatePipelineRequest extends AbstractBceRequest {

    private String         pipelineName = null;
    private String         description  = null;
    private String         sourceBucket = null;
    private String         targetBucket = null;
    private PipelineConfig config       = null;

    /**
     * 系统生成的队列唯一标识
     **/
    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipelineName) {
        checkStringNotEmpty(pipelineName, "The parameter pipelineName should NOT be null or empty string.");
        this.pipelineName = pipelineName;
    }

    public CreatePipelineRequest withPipelineName(String pipelineName) {
        checkStringNotEmpty(pipelineName, "The parameter pipelineName should NOT be null or empty string.");
        this.pipelineName = pipelineName;
        return this;
    }

    /**
     * pipeline描述
     **/
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CreatePipelineRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * pipeline的源Bucket
     **/
    public String getSourceBucket() {
        return sourceBucket;
    }

    public void setSourceBucket(String sourceBucket) {
        checkStringNotEmpty(sourceBucket, "The parameter sourceBucket should NOT be null or empty string.");
        this.sourceBucket = sourceBucket;
    }

    public CreatePipelineRequest withSourceBucket(String sourceBucket) {
        checkStringNotEmpty(sourceBucket, "The parameter sourceBucket should NOT be null or empty string.");
        this.sourceBucket = sourceBucket;
        return this;
    }

    /**
     * pipeline的目的Bucket
     **/
    public String getTargetBucket() {
        return targetBucket;
    }

    public void setTargetBucket(String targetBucket) {
        checkStringNotEmpty(targetBucket, "The parameter targetBucket should NOT be null or empty string.");
        this.targetBucket = targetBucket;
    }

    public CreatePipelineRequest withTargetBucket(String targetBucket) {
        checkStringNotEmpty(targetBucket, "The parameter targetBucket should NOT be null or empty string.");
        this.targetBucket = targetBucket;
        return this;
    }

    /**
     * pipeline的的配置
     **/
    public PipelineConfig getConfig() {
        return config;
    }

    public void setConfig(PipelineConfig config) {
        this.config = config;
    }

    public CreatePipelineRequest withConfig(PipelineConfig config) {
        this.config = config;
        return this;
    }

    public CreatePipelineRequest withRequestCredentials(
            BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreatePipelineRequest {\n");

        sb.append("    : ").append(pipelineName).append("\n");
        sb.append("    : ").append(description).append("\n");
        sb.append("    : ").append(sourceBucket).append("\n");
        sb.append("    : ").append(targetBucket).append("\n");
        sb.append("    : ").append(config).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
