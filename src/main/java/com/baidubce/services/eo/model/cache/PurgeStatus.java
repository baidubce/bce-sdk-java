package com.baidubce.services.eo.model.cache;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The status detail of cache purge task.
 */
public class PurgeStatus extends JsonObject {
    /**
     * The status.
     */
    private String status;

    /**
     * The submitted purge task info.
     */
    private PurgeTask task;

    /**
     * The created time of the purge task, UTC time.
     */
    private String createdAt;

    /**
     * The finished time of the purge task, UTC time.
     */
    private String finishedAt;

    /**
     * The user id who submitted the purge task.
     */
    private String operator;

    /**
     * The progress percentage of the purge task.
     */
    private Integer progress;

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
    public PurgeTask getTask() {
        return task;
    }

    /**
     * @param task
     */
    public void setTask(PurgeTask task) {
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
}
