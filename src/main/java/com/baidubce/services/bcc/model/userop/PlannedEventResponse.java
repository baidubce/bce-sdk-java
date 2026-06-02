/*
 * Copyright 2025 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model.userop;

import com.baidubce.services.tag.model.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;


public class PlannedEventResponse {
    /**
     * event Id
     */
    private String serverEventId;

    /**
     * Event type
     */
    private String serverEventType;

    /**
     * event status
     */
    private String serverEventStatus;

    /**
     * The id of instance.
     */
    private String instanceId;

    /**
     * Fault instance product type
     */
    private String productCategory;

    /**
     * The instance of spec.
     */
    private String instanceSpec;

    /**
     * The name of the instance
     */
    private String instanceName;

    /**
     * the privateIp of the backendServer.
     */
    private String privateIp;

    /**
     * The tags of the instance
     */
    private List<Tag> tags;

    /**
     * server event create time
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Date serverEventCreatedTime;

    /**
     * server event end time
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Date serverEventEndedTime;

    /**
     * The operation and maintenance operations supported by this event
     */
    private List<String> maintenanceOptions;

    /**
     * Authorized operation and maintenance operations by users
     */
    private List<String> supportMaintenanceOptions;

    /**
     * Authorized operation and maintenance methods for this event
     */
    private String authorizedMaintenanceOperation;

    /**
     * List of operation and planned event Ids
     */
    private List<String> associatedPlannedMaintenanceServerEventIds;

    /**
     * List of operation and unplanned event Ids
     */
    private List<String> associatedUnplannedMaintenanceServerEventIds;

    /**
     * Event execution time
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Date executeTime;

    /**
     * Operation logs, including user authorization, operation and maintenance, acceptance and other operation records.
     */
    private List<OperationRecordResponse> serverEventLogs;

    /**
     * Event risk items
     */
    private List<IssueResponse> risks;

    public void setServerEventId(String serverEventId) {
        this.serverEventId = serverEventId;
    }

    public String getServerEventId() {
        return this.serverEventId;
    }

    public void setServerEventType(String serverEventType) {
        this.serverEventType = serverEventType;
    }

    public String getServerEventType() {
        return this.serverEventType;
    }

    public void setServerEventStatus(String serverEventStatus) {
        this.serverEventStatus = serverEventStatus;
    }

    public String getServerEventStatus() {
        return this.serverEventStatus;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceId() {
        return this.instanceId;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductCategory() {
        return this.productCategory;
    }

    public void setInstanceSpec(String instanceSpec) {
        this.instanceSpec = instanceSpec;
    }

    public String getInstanceSpec() {
        return this.instanceSpec;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getInstanceName() {
        return this.instanceName;
    }

    public void setPrivateIp(String privateIp) {
        this.privateIp = privateIp;
    }

    public String getPrivateIp() {
        return this.privateIp;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setServerEventCreatedTime(Date serverEventCreatedTime) {
        this.serverEventCreatedTime = serverEventCreatedTime;
    }

    public Date getServerEventCreatedTime() {
        return this.serverEventCreatedTime;
    }

    public void setServerEventEndedTime(Date serverEventEndedTime) {
        this.serverEventEndedTime = serverEventEndedTime;
    }

    public Date getServerEventEndedTime() {
        return this.serverEventEndedTime;
    }

    public void setMaintenanceOptions(List<String> maintenanceOptions) {
        this.maintenanceOptions = maintenanceOptions;
    }

    public List<String> getMaintenanceOptions() {
        return this.maintenanceOptions;
    }

    public void setSupportMaintenanceOptions(List<String> supportMaintenanceOptions) {
        this.supportMaintenanceOptions = supportMaintenanceOptions;
    }

    public List<String> getSupportMaintenanceOptions() {
        return this.supportMaintenanceOptions;
    }

    public void setAuthorizedMaintenanceOperation(String authorizedMaintenanceOperation) {
        this.authorizedMaintenanceOperation = authorizedMaintenanceOperation;
    }

    public String getAuthorizedMaintenanceOperation() {
        return this.authorizedMaintenanceOperation;
    }

    public void setAssociatedPlannedMaintenanceServerEventIds(List<String> associatedPlannedMaintenanceServerEventIds) {
        this.associatedPlannedMaintenanceServerEventIds = associatedPlannedMaintenanceServerEventIds;
    }

    public List<String> getAssociatedPlannedMaintenanceServerEventIds() {
        return this.associatedPlannedMaintenanceServerEventIds;
    }

    public void setAssociatedUnplannedMaintenanceServerEventIds(List<String> associatedUnplannedMaintenanceServerEventIds) {
        this.associatedUnplannedMaintenanceServerEventIds = associatedUnplannedMaintenanceServerEventIds;
    }

    public List<String> getAssociatedUnplannedMaintenanceServerEventIds() {
        return this.associatedUnplannedMaintenanceServerEventIds;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public Date getExecuteTime() {
        return this.executeTime;
    }

    public void setServerEventLogs(List<OperationRecordResponse> serverEventLogs) {
        this.serverEventLogs = serverEventLogs;
    }

    public List<OperationRecordResponse> getServerEventLogs() {
        return this.serverEventLogs;
    }

    public void setRisks(List<IssueResponse> risks) {
        this.risks = risks;
    }

    public List<IssueResponse> getRisks() {
        return this.risks;
    }

    @Override
    public String toString() {
        return "PlannedEventResponse{"
                + "serverEventId=" + serverEventId + "\n"
                + "serverEventType=" + serverEventType + "\n"
                + "serverEventStatus=" + serverEventStatus + "\n"
                + "instanceId=" + instanceId + "\n"
                + "productCategory=" + productCategory + "\n"
                + "instanceSpec=" + instanceSpec + "\n"
                + "instanceName=" + instanceName + "\n"
                + "privateIp=" + privateIp + "\n"
                + "tags=" + tags + "\n"
                + "serverEventCreatedTime=" + serverEventCreatedTime + "\n"
                + "serverEventEndedTime=" + serverEventEndedTime + "\n"
                + "maintenanceOptions=" + maintenanceOptions + "\n"
                + "supportMaintenanceOptions=" + supportMaintenanceOptions + "\n"
                + "authorizedMaintenanceOperation=" + authorizedMaintenanceOperation + "\n"
                + "associatedPlannedMaintenanceServerEventIds=" + associatedPlannedMaintenanceServerEventIds + "\n"
                + "associatedUnplannedMaintenanceServerEventIds=" + associatedUnplannedMaintenanceServerEventIds + "\n"
                + "executeTime=" + executeTime + "\n"
                + "serverEventLogs=" + serverEventLogs + "\n"
                + "risks=" + risks + "\n"
                + "}";
    }

}