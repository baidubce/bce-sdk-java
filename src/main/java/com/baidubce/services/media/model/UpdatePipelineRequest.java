/*
 * Copyright 2020 Baidu, Inc.
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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import static com.baidubce.util.Validate.checkStringNotEmpty;

/**
 * The request for updating pipeline
 */
@Data
public class UpdatePipelineRequest extends AbstractBceRequest {

    private String         pipelineName = null;
    private String         description  = null;
    private String         sourceBucket = null;
    private String         targetBucket = null;
    private PipelineConfig config       = null;


    public UpdatePipelineRequest withPipelineName(String pipelineName) {
        checkStringNotEmpty(pipelineName, "The parameter pipelineName should NOT be null or empty string.");
        this.pipelineName = pipelineName;
        return this;
    }

    public UpdatePipelineRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    public UpdatePipelineRequest withSourceBucket(String sourceBucket) {
        checkStringNotEmpty(sourceBucket, "The parameter sourceBucket should NOT be null or empty string.");
        this.sourceBucket = sourceBucket;
        return this;
    }

    public UpdatePipelineRequest withTargetBucket(String targetBucket) {
        checkStringNotEmpty(targetBucket, "The parameter targetBucket should NOT be null or empty string.");
        this.targetBucket = targetBucket;
        return this;
    }

    public UpdatePipelineRequest withConfig(PipelineConfig config) {
        this.config = config;
        return this;
    }

    public UpdatePipelineRequest withPipeline(PipelineStatus pipeline) {
        this.pipelineName = pipeline.getPipelineName();
        this.description = pipeline.getDescription();
        this.sourceBucket = pipeline.getSourceBucket();
        this.targetBucket = pipeline.getTargetBucket();
        this.config = pipeline.getConfig();
        return this;
    }

    public UpdatePipelineRequest withRequestCredentials(
            BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UpdatePipelineRequest {\n");

        sb.append("    : ").append(pipelineName).append("\n");
        sb.append("    : ").append(description).append("\n");
        sb.append("    : ").append(sourceBucket).append("\n");
        sb.append("    : ").append(targetBucket).append("\n");
        sb.append("    : ").append(config).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
