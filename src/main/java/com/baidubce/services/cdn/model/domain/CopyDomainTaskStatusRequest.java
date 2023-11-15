package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

public class CopyDomainTaskStatusRequest extends CdnRequest {
    private String taskId;

    public CopyDomainTaskStatusRequest() {
    }

    public CopyDomainTaskStatusRequest(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public CopyDomainTaskStatusRequest withTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }
}
