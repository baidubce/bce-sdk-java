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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for list release record.
 */
public class ListReleaseRecordRequest extends AbstractBceRequest {

    /**
     * The id of workspace.
     */
    private String workspaceID;

    /**
     * The id of application.
     */
    private String applicationID;

    /**
     * The order by info. eg: startTime
     */
    private String orderBy;

    /**
     * The order info. eg: desc or asc.
     */
    private String order;

    /**
     * The page size.
     */
    private String pageSize = "10";

    /**
     * The page no.
     */
    private String pageNo = "1";

    /**
     * The operator.
     */
    private String executor;

    /**
     * The status of release record. eg: RUNNING,PENDING,PAUSED,TERMINATED,SUCCESS
     */
    private String status;

    /**
     * The type of release record.
     */
    private String type;

    /**
     * The start time.
     */
    private Date startTime;

    /**
     * The end time.
     */
    private Date endTime;

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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public ListReleaseRecordRequest withWorkspaceID(String workspaceID) {
        this.setWorkspaceID(workspaceID);
        return this;
    }

    public ListReleaseRecordRequest withApplicationID(String applicationID) {
        this.setApplicationID(applicationID);
        return this;
    }

    public ListReleaseRecordRequest withOrderBy(String orderBy) {
        this.setOrder(orderBy);
        return this;
    }

    public ListReleaseRecordRequest withOrder(String order) {
        this.setOrder(order);
        return this;
    }

    public ListReleaseRecordRequest withPageSize(String pageSize) {
        this.setPageSize(pageSize);
        return this;
    }

    public ListReleaseRecordRequest withPageNo(String pageNo) {
        this.setPageNo(pageNo);
        return this;
    }

    public ListReleaseRecordRequest withExecutor(String executor) {
        this.setExecutor(executor);
        return this;
    }

    public ListReleaseRecordRequest withStatus(String status) {
        this.setStatus(status);
        return this;
    }

    public ListReleaseRecordRequest withType(String type) {
        this.setType(type);
        return this;
    }

    public ListReleaseRecordRequest withStartTime(Date startTime) {
        this.setStartTime(startTime);
        return this;
    }

    public ListReleaseRecordRequest withEndTime(Date endTime) {
        this.setEndTime(endTime);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public ListReleaseRecordRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
