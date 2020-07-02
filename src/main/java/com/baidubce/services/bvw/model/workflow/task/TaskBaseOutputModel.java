/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.bvw.model.workflow.task;

import java.util.Date;

import com.baidubce.services.bvw.model.common.UtcTime;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The stage task base output.
 */
public class TaskBaseOutputModel {

    /**
     * The task id.
     */
    private String taskId;
    /**
     * The task is successful or not.
     */
    private boolean success;
    /**
     * The task output json string.
     */
    private String output;
    /**
     * If task is failed, will save the error message.
     */
    private String errorMsg;
    /**
     * The begin time of task.
     */
    @UtcTime
    private Date beginTime;
    /**
     * The end time of task.
     */
    @UtcTime
    private Date endTime;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("taskId", taskId)
                .append("success", success)
                .append("output", output)
                .append("errorMsg", errorMsg)
                .append("beginTime", beginTime)
                .append("endTime", endTime)
                .toString();
    }

}
