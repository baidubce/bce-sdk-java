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

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for rollback release record.
 */
public class RollbackReleaseRecordRequest extends AbstractBceRequest {

    /**
     * The id of workspace.
     */
    private String workspaceID;

    /**
     * The id of application.
     */
    private String applicationID;

    /**
     * The id of release record.
     */
    private String releaseRecordID;

    /**
     * The description of rollback.
     */
    private String description;

    /**
     * The task info of rollback.
     */
    private List<TaskReqModel> tasks;

    public String getWorkspaceID() {
        return workspaceID;
    }

    public void setWorkspaceID(String workspaceID) {
        this.workspaceID = workspaceID;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TaskReqModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskReqModel> tasks) {
        this.tasks = tasks;
    }

    public String getReleaseRecordID() {
        return releaseRecordID;
    }

    public void setReleaseRecordID(String releaseRecordID) {
        this.releaseRecordID = releaseRecordID;
    }

    public RollbackReleaseRecordRequest withWorkspaceID(String workspaceID) {
        this.setWorkspaceID(workspaceID);
        return this;
    }

    public RollbackReleaseRecordRequest withApplicationID(String applicationID) {
        this.setApplicationID(applicationID);
        return this;
    }

    public RollbackReleaseRecordRequest withReleaseRecordID(String releaseRecordID) {
        this.setReleaseRecordID(releaseRecordID);
        return this;
    }

    public RollbackReleaseRecordRequest withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public RollbackReleaseRecordRequest addTaskReqModel(TaskReqModel model) {
        if (CollectionUtils.isEmpty(tasks)) {
            tasks = new LinkedList<TaskReqModel>();
        }
        tasks.add(model);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public RollbackReleaseRecordRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
