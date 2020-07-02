package com.baidubce.services.cnap.model.deploygroup;

import java.util.Date;

/**
 * The model for deploy task.
 */
public class DeployTaskModel {
    /**
     * The id of task.
     */
    private String taskID;

    /**
     * The type of task.
     */
    private String type;

    /**
     * The id of deploy group.
     */
    private String deployGroupID;

    /**
     * The name of deploy group.
     */
    private String deployGroupName;

    /**
     * The create time.
     */
    private Date createdAt;

    /**
     * The update time.
     */
    private Date updatedAt;

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeployGroupID() {
        return deployGroupID;
    }

    public void setDeployGroupID(String deployGroupID) {
        this.deployGroupID = deployGroupID;
    }

    public String getDeployGroupName() {
        return deployGroupName;
    }

    public void setDeployGroupName(String deployGroupName) {
        this.deployGroupName = deployGroupName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
