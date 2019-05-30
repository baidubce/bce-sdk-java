package com.baidubce.services.tsdb.model;

import java.util.Date;

/**
 * Represent the response for getting task.
 */
public class GetTaskResponse {

    private String taskId;

    private String type;

    private TaskParams params;

    private String status;

    private TaskResult results;

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

    public TaskParams getParams() {
        return params;
    }

    public void setParams(TaskParams params) {
        this.params = params;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TaskResult getResults() {
        return results;
    }

    public void setResults(TaskResult results) {
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
