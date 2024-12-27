package com.baidubce.services.vod.v2.model;

import com.baidubce.model.AbstractBceResponse;

public class GenericTaskResponse extends AbstractBceResponse {

    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

}
