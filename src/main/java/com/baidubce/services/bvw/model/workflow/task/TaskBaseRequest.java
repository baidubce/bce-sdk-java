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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Base task request.
 */
public class TaskBaseRequest extends AbstractBceRequest {

    /**
     * The task id.
     */
    private String taskId;

    /**
     * Construct a base task request with specified parameters.
     * @param taskId The task id
     * @return A base task request
     */
    public static TaskBaseRequest of(String taskId) {
        TaskBaseRequest baseRequest = new TaskBaseRequest();
        baseRequest.setTaskId(taskId);
        return baseRequest;
    }

    @Override
    public TaskBaseRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

}
