package com.baidubce.services.vod.callback;

import java.util.Date;

public abstract class AbstractMediaProcessCompleteEvent {

    private String taskId;
    private String status;
    private Date createTime;
    private Date finishTime;
    private Date beginProcessTime;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getBeginProcessTime() {
        return beginProcessTime;
    }

    public void setBeginProcessTime(Date beginProcessTime) {
        this.beginProcessTime = beginProcessTime;
    }

}
