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
 * The model for task progress.
 */
public class TaskProgressModel {

    /**
     * The id of task.
     */
    private String taskID;

    /**
     * The progress of task.
     */
    private String progress;

    /**
     * The flag which indicate the task is current.
     */
    private boolean currentTask;

    /**
     * The status of task.
     */
    private String status;

    /**
     * The start time.
     */
    private Date startTime;

    /**
     * The end time.
     */
    private Date endTime;

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }


    public boolean getCurrentTask() {
        return this.currentTask;
    }

    public void setCurrentTask(boolean currentTask) {
        this.currentTask = currentTask;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
