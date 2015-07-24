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

public class ThumbnailJobStatus {

    private String jobId = null;
    
    private String jobStatus = null;
    
    private String pipelineName = null;
    
    private ThumbnailSource source = null;
    
    private ThumbnailTargetStatus target = null;
    
    private ThumbnailCapture capture = null;
    

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipeline) {
        this.pipelineName = pipeline;
    }

    public ThumbnailSource getSource() {
        return source;
    }

    public void setSource(ThumbnailSource source) {
        this.source = source;
    }

    public ThumbnailTargetStatus getTarget() {
        return target;
    }

    public void setTarget(ThumbnailTargetStatus target) {
        this.target = target;
    }

    public ThumbnailCapture getCapture() {
        return capture;
    }

    public void setCapture(ThumbnailCapture capture) {
        this.capture = capture;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ThumbnailJobStatus {\n");
        sb.append("    jobId: ").append(jobId).append("\n");
        sb.append("    jobStatus: ").append(jobStatus).append("\n");
        sb.append("    pipeline: ").append(pipelineName).append("\n");
        sb.append("    source: ").append(source).append("\n");
        sb.append("    target: ").append(target).append("\n");
        sb.append("    capture: ").append(capture).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}
