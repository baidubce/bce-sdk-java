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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class CreateThumbnailJobRequest extends AbstractBceRequest {
    
    private String pipelineName = null;

    private ThumbnailSource source = null;
    
    private ThumbnailTarget target = null;
    
    private ThumbnailCapture capture = null;

    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
    }

    public CreateThumbnailJobRequest withPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
        return this;
    }

    public ThumbnailSource getSource() {
        return source;
    }

    public void setSource(ThumbnailSource source) {
        this.source = source;
    }

    public CreateThumbnailJobRequest withSource(ThumbnailSource source) {
        this.source = source;
        return this;
    }

    public ThumbnailTarget getTarget() {
        return target;
    }

    public void setTarget(ThumbnailTarget target) {
        this.target = target;
    }

    public CreateThumbnailJobRequest withTarget(ThumbnailTarget target) {
        this.target = target;
        return this;
    }

    public ThumbnailCapture getCapture() {
        return capture;
    }

    public void setCapture(ThumbnailCapture capture) {
        this.capture = capture;
    }

    public CreateThumbnailJobRequest withCapture(ThumbnailCapture capture) {
        this.capture = capture;
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
