/*
 * Copyright 2015 Baidu, Inc.
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

import com.baidubce.model.AbstractBceResponse;

public class GetThumbnailJobResponse extends AbstractBceResponse {
    /**
     * the thumbnail job Id
     **/
    private String jobId = null;

    /**
     * the thumbnail job status
     **/
    private String jobStatus = null;

    /**
     * the pipeline name of the thumbnail job
     **/
    private String pipelineName = null;

    /**
     * the source information of the thumbnail job
     **/
    private ThumbnailSource source = null;

    /**
     * the target information of the  thumbnail job
     **/
    private ThumbnailTargetStatus target = null;

    /**
     * the information that tells how to pick the thumbnails
     **/
    private ThumbnailCapture capture = null;

    /**
     * delogo area
     **/
    private Area delogoArea = null;

    /**
     * crop area
     **/
    private Area crop = null;

    /**
     * error information
     **/
    private MediaError error = null;

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

    public void setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
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

    public Area getDelogoArea() {
        return delogoArea;
    }

    public void setDelogoArea(Area delogoArea) {
        this.delogoArea = delogoArea;
    }

    public Area getCrop() {
        return crop;
    }

    public void setCrop(Area crop) {
        this.crop = crop;
    }

    public MediaError getError() {
        return error;
    }

    public void setError(MediaError error) {
        this.error = error;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetThumbnailJobResponse {\n");
        sb.append("    jobId: ").append(jobId).append("\n");
        sb.append("    jobStatus: ").append(jobStatus).append("\n");
        sb.append("    pipeline: ").append(pipelineName).append("\n");
        sb.append("    source: ").append(source).append("\n");
        sb.append("    target: ").append(target).append("\n");
        sb.append("    capture: ").append(capture).append("\n");
        if (delogoArea != null) {
            sb.append("    delogoArea: ").append(delogoArea).append("\n");
        }
        if (crop != null) {
            sb.append("    crop: ").append(crop).append("\n");
        }
        if (error != null) {
            sb.append(error).append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    
}
