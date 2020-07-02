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

/**
 * The model for release record with deploy group.
 */
public class ReleaseRecordWithDeployGroupModel {

    /**
     * The id of release record.
     */
    private String releaseRecordID;

    /**
     * The operator of release record.
     */
    private String executor;

    /**
     * The id of application.
     */
    private String applicationID;

    /**
     * The status of release record.
     */
    private String status;

    /**
     * The id of workspace.
     */
    private String workspaceID;

    /**
     * The type of release record.
     */
    private String type;

    /**
     * The description of release record.
     */
    private String description;

    /**
     * The custom info.
     */
    private String handleReason;

    /**
     * The id of release record transaction.
     */
    private String transactionID;

    /**
     * The custom info.
     */
    private Date planDeployTime;

    /**
     * The start time.
     */
    private Date startTime;

    /**
     * The end time.
     */
    private Date endTime;

    /**
     * The deploy group info.
     */
    private List<DeployGroupBaseInfoModel> deployGroups;

    public String getReleaseRecordID() {
        return releaseRecordID;
    }

    public void setReleaseRecordID(String releaseRecordID) {
        this.releaseRecordID = releaseRecordID;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWorkspaceID() {
        return workspaceID;
    }

    public void setWorkspaceID(String workspaceID) {
        this.workspaceID = workspaceID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHandleReason() {
        return handleReason;
    }

    public void setHandleReason(String handleReason) {
        this.handleReason = handleReason;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public Date getPlanDeployTime() {
        return planDeployTime;
    }

    public void setPlanDeployTime(Date planDeployTime) {
        this.planDeployTime = planDeployTime;
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

    public List<DeployGroupBaseInfoModel> getDeployGroups() {
        return deployGroups;
    }

    public void setDeployGroups(
            List<DeployGroupBaseInfoModel> deployGroups) {
        this.deployGroups = deployGroups;
    }
}
