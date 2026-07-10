package com.baidubce.services.eo.model.cache;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The status detail of cache prefetch task.
 */
public class PrefetchStatus extends JsonObject {
    /**
     * The status.
     */
    private String status;

    /**
     * The submitted prefetch task info.
     */
    private PrefetchTask task;

    /**
     * The created time of the prefetch task, UTC time.
     */
    private String createdAt;

    /**
     * The started time of the prefetch task, UTC time.
     */
    private String startedAt;

    /**
     * The finished time of the prefetch task, UTC time.
     */
    private String finishedAt;

    /**
     * The user id who submitted the prefetch task.
     */
    private String operator;

    /**
     * The progress percentage of the prefetch task.
     */
    private Integer progress;

    /**
     * The error reason when prefetch failed.
     */
    private String reason;

    /**
     * The task id of a single url.
     */
    private String id;

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return task
     */
    public PrefetchTask getTask() {
        return task;
    }

    /**
     * @param task
     */
    public void setTask(PrefetchTask task) {
        this.task = task;
    }

    /**
     * @return createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return startedAt
     */
    public String getStartedAt() {
        return startedAt;
    }

    /**
     * @param startedAt
     */
    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    /**
     * @return finishedAt
     */
    public String getFinishedAt() {
        return finishedAt;
    }

    /**
     * @param finishedAt
     */
    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    /**
     * @return operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @param operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * @return progress
     */
    public Integer getProgress() {
        return progress;
    }

    /**
     * @param progress
     */
    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    /**
     * @return reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
}
