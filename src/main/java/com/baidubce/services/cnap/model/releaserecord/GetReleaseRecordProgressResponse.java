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
import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * The response for get release record progress.
 */
public class GetReleaseRecordProgressResponse extends AbstractBceResponse {
    /**
     * The status.
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

    /**
     * The task progress info.
     */
    private List<TaskProgressModel> tasksProgress;

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

    public List<TaskProgressModel> getTasksProgress() {
        return tasksProgress;
    }

    public void setTasksProgress(List<TaskProgressModel> tasksProgress) {
        this.tasksProgress = tasksProgress;
    }
}
