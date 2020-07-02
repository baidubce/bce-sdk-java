/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.baidubce.services.cnap.model.releaserecord;

import java.util.Date;

/**
 * The model for task base info.
 */
public class TaskBaseInfoModel {
    /**
     * The id of task.
     */
    private String taskID;

    /**
     * The type of type.
     */
    private String type;

    /**
     * The id of release record.
     */
    private String releaseRecordID;

    /**
     * The status of task.
     */
    private String status;

    /**
     * The pre id of task.
     */
    private String preTaskID;

    /**
     * The action after task.
     */
    private int pendAfterTask;

    /**
     * The name of task.
     */
    private String name;

    /**
     * The process of task.
     */
    private String progress;

    /**
     * The start time.
     */
    private Date startTime;

    /**
     * The end time.
     */
    private Date endTime;

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

    public String getReleaseRecordID() {
        return releaseRecordID;
    }

    public void setReleaseRecordID(String releaseRecordID) {
        this.releaseRecordID = releaseRecordID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPreTaskID() {
        return preTaskID;
    }

    public void setPreTaskID(String preTaskID) {
        this.preTaskID = preTaskID;
    }

    public int getPendAfterTask() {
        return pendAfterTask;
    }

    public void setPendAfterTask(int pendAfterTask) {
        this.pendAfterTask = pendAfterTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
