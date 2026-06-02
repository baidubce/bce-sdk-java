package com.baidubce.services.bcc.model.task;


import java.util.Date;

public class BaseTaskUO {

    private String taskId;

    private String taskAction;

    private String taskStatus;

    private Date createdTime;

    private Date finishedTime;

    /**
     * 子任务总数
     */
    private Integer totalCount;

    /**
     * 子任务成功数
     */
    private Integer successCount;

    /**
     * 子任务失败数
     */
    private Integer failedCount;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskAction() {
        return taskAction;
    }

    public void setTaskAction(String taskAction) {
        this.taskAction = taskAction;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(Date finishedTime) {
        this.finishedTime = finishedTime;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(Integer failedCount) {
        this.failedCount = failedCount;
    }

    @Override
    public String toString() {
        return "BaseTaskUO{" +
                "taskId='" + taskId + '\'' +
                ", taskAction='" + taskAction + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                ", createdTime=" + createdTime +
                ", finishedTime=" + finishedTime +
                ", totalCount=" + totalCount +
                ", successCount=" + successCount +
                ", failedCount=" + failedCount +
                '}';
    }
}
