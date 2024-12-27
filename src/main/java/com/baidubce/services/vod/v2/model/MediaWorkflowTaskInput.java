package com.baidubce.services.vod.v2.model;

public class MediaWorkflowTaskInput {

    private String workflowId;

    public MediaWorkflowTaskInput() {
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public MediaWorkflowTaskInput withWorkflowId(String workflowId) {
        this.workflowId = workflowId;
        return this;
    }

}
