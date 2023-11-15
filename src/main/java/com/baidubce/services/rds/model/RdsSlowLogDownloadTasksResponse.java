package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * The response of rds slow log download tasks
 */
public class RdsSlowLogDownloadTasksResponse extends AbstractBceResponse {

    @JsonProperty("slowlogs")
    private List<RdsSlowLogDownloadTask> slowLogs;

    public List<RdsSlowLogDownloadTask> getSlowLogs() {
        return slowLogs;
    }

    public void setSlowLogs(List<RdsSlowLogDownloadTask> slowLogs) {
        this.slowLogs = slowLogs;
    }
}
