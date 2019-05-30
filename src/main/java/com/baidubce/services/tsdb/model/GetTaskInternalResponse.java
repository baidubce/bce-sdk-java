package com.baidubce.services.tsdb.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.Date;

/**
 * Represent the internal response for getting task.
 */
public class GetTaskInternalResponse extends AbstractBceResponse {

    private String taskId;

    private String type;

    private String params;

    private String status;

    private String results;

    private TaskError error;

    private Date creteTime;

    private Date completionTime;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public TaskError getError() {
        return error;
    }

    public void setError(TaskError error) {
        this.error = error;
    }

    public Date getCreteTime() {
        return creteTime;
    }

    public void setCreteTime(Date creteTime) {
        this.creteTime = creteTime;
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }
}
