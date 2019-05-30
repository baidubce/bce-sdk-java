package com.baidubce.services.tsdb.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * Represent the response for exporting datapoints.
 */
public class ExportDatapointsResponse extends AbstractBceResponse {

    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
