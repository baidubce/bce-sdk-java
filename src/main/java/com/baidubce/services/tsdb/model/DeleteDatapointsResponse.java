package com.baidubce.services.tsdb.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * Represent the response for deleting datapoints.
 */
public class DeleteDatapointsResponse extends AbstractBceResponse {

    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
