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
package com.baidubce.services.bvw.model.workflow;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * List workflow request
 */
public class WorkflowListRequest extends AbstractBceRequest {

    /**
     * The page number of list by page request.
     */
    private int pageNo;
    /**
     * The number of element in each page.
     */
    private int pageSize;
    /**
     * The workflow status.
     */
    private WorkflowStatus status;
    /**
     * The workflow name, support fuzzy query.
     */
    private String name;
    /**
     * The begin time of creating workflow time, using UTC format.
     * eg. "2019-04-07T16:00:00Z"
     */
    private String beginTime;
    /**
     * The end time of creating workflow time, using UTC format.
     * eg. "2019-04-07T16:00:00Z"
     */
    private String endTime;

    /**
     * Construct a list workflow request with specified parameters..
     *
     * @param pageNo    The number of page
     * @param pageSize  The number of elements in each page
     * @param status    The workflow status
     * @param name      The workflow name
     * @param beginTime The begin time of creating workflow time
     * @param endTime   The end time of creating workflow time
     * @return A workflow list request
     */
    public static WorkflowListRequest of(int pageNo, int pageSize, WorkflowStatus status, String name, String beginTime,
                                         String endTime) {
        WorkflowListRequest listRequest = new WorkflowListRequest();
        listRequest.setPageNo(pageNo);
        listRequest.setPageSize(pageSize);
        listRequest.setStatus(status);
        listRequest.setBeginTime(beginTime);
        listRequest.setEndTime(endTime);
        listRequest.setName(name);
        return listRequest;
    }

    /**
     * Construct a list workflow request with specified parameters..
     *
     * @param pageNo   The number of page
     * @param pageSize The number of elements in each page
     * @return A workflow list request
     */
    public static WorkflowListRequest of(int pageNo, int pageSize) {
        return of(pageNo, pageSize, null, null, null, null);
    }

    /**
     * Construct a list workflow request with specified parameters..
     *
     * @param pageNo    The number of page
     * @param pageSize  The number of elements in each page
     * @param name      The workflow name
     * @return A workflow list request
     */
    public static WorkflowListRequest of(int pageNo, int pageSize, String name) {
        return of(pageNo, pageSize, null, name, null, null);
    }

    /**
     * Construct a list workflow request with specified parameters..
     *
     * @param pageNo    The number of page
     * @param pageSize  The number of elements in each page
     * @param status    The workflow status
     * @return A workflow list request
     */
    public static WorkflowListRequest of(int pageNo, int pageSize, WorkflowStatus status) {
        return of(pageNo, pageSize, status, null, null, null);
    }

    /**
     * Construct a list workflow request with specified parameters..
     *
     * @param pageNo    The number of page
     * @param pageSize  The number of elements in each page
     * @param beginTime The begin time of creating workflow time
     * @param endTime   The end time of creating workflow time
     * @return A workflow list request
     */
    public static WorkflowListRequest of(int pageNo, int pageSize, String beginTime, String endTime) {
        return of(pageNo, pageSize, null, null, beginTime, endTime);
    }

    @Override
    public WorkflowListRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public WorkflowStatus getStatus() {
        return status;
    }

    public void setStatus(WorkflowStatus status) {
        this.status = status;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
