/*
 * Copyright 2016 Baidu, Inc.
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

package com.baidubce.services.cdn.model;

import java.util.Date;

/**
 * @author yixing
 *
 */
public class PurgeStatus extends JsonObject {
    private String status;
    private PurgeTask task;
    private Date createdAt;
    private Date finishedAt;
    private int progress;
    
    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * @param task
     */
    public void setTask(PurgeTask task) {
        this.task = task;
    }
    
    /**
     * @return task
     */
    public PurgeTask getTask() {
        return task;
    }
    
    /**
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    /**
     * @return createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param finishedAt
     */
    public void setFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
    }
    
    /**
     * @return finishedAt
     */
    public Date getFinishedAt() {
        return finishedAt;
    }
    
    /**
     * @param progress
     */
    public void setProgress(int progress) {
        this.progress = progress;
    }
    
    /**
     * @return progress
     */
    public int getProgress() {
        return progress;
    }
}
