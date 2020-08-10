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

import lombok.Data;

@Data
public class SubtitleJobStatus {
    /**
     * the subtitle job Id
     **/
    private String jobId = null;

    /**
     * the subtitle job status
     **/
    private String jobStatus = null;

    /**
     * The pipeline name of the subtitle job
     **/
    private String pipelineName = null;

    /**
     * The source information of the subtitle job
     **/
    private SubtitleSource source = null;

    /**
     * The target configuration of the  subtitle job
     **/
    private SubtitleTargetStatus target = null;

    /**
     * The extra configuration of subtitle job
     **/
    private SubtitleConfig config = null;

    /**
     * error information
     **/
    private MediaError error = null;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetSubtitleJobResponse {\n");
        sb.append("    jobId: ").append(jobId).append("\n");
        sb.append("    jobStatus: ").append(jobStatus).append("\n");
        sb.append("    pipeline: ").append(pipelineName).append("\n");
        sb.append("    source: ").append(source).append("\n");
        sb.append("    target: ").append(target).append("\n");
        sb.append("    config: ").append(config).append("\n");
        if (error != null) {
            sb.append("    error: ").append(error).append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }
    
}
