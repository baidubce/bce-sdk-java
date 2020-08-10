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

/**
 * The request containing all options for creating a subtitle job.
 */
@Data
public class CreateSubtitleJobRequest extends AbstractBceRequest {
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
    private SubtitleTarget target = null;

    /**
     * The extra configuration of subtitle job
     **/
    private SubtitleConfig config = null;

    public CreateSubtitleJobRequest withPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
        return this;
    }


    public CreateSubtitleJobRequest withSource(SubtitleSource source) {
        this.source = source;
        return this;
    }

    public CreateSubtitleJobRequest withTarget(SubtitleTarget target) {
        this.target = target;
        return this;
    }

    public CreateSubtitleJobRequest withConfig(SubtitleConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public CreateSubtitleJobRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
