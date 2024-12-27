package com.baidubce.services.vod.v2.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class MediaProcessRequest extends AbstractBceRequest {

    private String mediaId;

    private MediaWorkflowTaskInput workflow;

    private MediaPresetTaskInput preset;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public MediaWorkflowTaskInput getWorkflow() {
        return workflow;
    }

    public void setWorkflow(MediaWorkflowTaskInput workflow) {
        this.workflow = workflow;
    }

    public MediaPresetTaskInput getPreset() {
        return preset;
    }

    public void setPreset(MediaPresetTaskInput preset) {
        this.preset = preset;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public MediaProcessRequest withMediaId(String mediaId) {
        this.mediaId = mediaId;
        return this;
    }

    public MediaProcessRequest withWorkflow(MediaWorkflowTaskInput workflow) {
        this.workflow = workflow;
        return this;
    }

    public MediaProcessRequest withPreset(MediaPresetTaskInput preset) {
        this.preset = preset;
        return this;
    }

}
