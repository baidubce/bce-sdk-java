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

import com.baidubce.auth.BceCredentials;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class DescribeServerEventReq extends ListRequest {

    /**
     * List of server event Ids
     */
    private List<String> serverEventIds;

    /**
     * instance ids for query
     */
    private List<String> instanceIds;

    /**
     * Fault instance product type
     */
    private String productCategory;

    /**
     * Event type
     */
    private String serverEventType;

    /**
     * Time log type
     */
    private String serverEventLogTimeFilter;

    /**
     * Start Time, format is yyyy-MM-dd'T'HH:mm:ss'Z'
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Date periodStartTime;

    /**
     * End Time, format is yyyy-MM-dd'T'HH:mm:ss.SSSXXX
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Date periodEndTime;

    /**
     * event status
     */
    private String serverEventStatus;

    public void setServerEventIds(List<String> serverEventIds) {
        this.serverEventIds = serverEventIds;
    }

    public List<String> getServerEventIds() {
        return this.serverEventIds;
    }

    public void setInstanceIds(List<String> instanceIds) {
        this.instanceIds = instanceIds;
    }

    public List<String> getInstanceIds() {
        return this.instanceIds;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductCategory() {
        return this.productCategory;
    }

    public void setServerEventType(String serverEventType) {
        this.serverEventType = serverEventType;
    }

    public String getServerEventType() {
        return this.serverEventType;
    }

    public void setServerEventLogTimeFilter(String serverEventLogTimeFilter) {
        this.serverEventLogTimeFilter = serverEventLogTimeFilter;
    }

    public String getServerEventLogTimeFilter() {
        return this.serverEventLogTimeFilter;
    }

    public void setPeriodStartTime(Date periodStartTime) {
        this.periodStartTime = periodStartTime;
    }

    public Date getPeriodStartTime() {
        return this.periodStartTime;
    }

    public void setPeriodEndTime(Date periodEndTime) {
        this.periodEndTime = periodEndTime;
    }

    public Date getPeriodEndTime() {
        return this.periodEndTime;
    }

    public void setServerEventStatus(String serverEventStatus) {
        this.serverEventStatus = serverEventStatus;
    }

    public String getServerEventStatus() {
        return this.serverEventStatus;
    }

    @Override
    public String toString() {
        return "DescribeServerEventV3Req{"
                + "serverEventIds=" + serverEventIds + "\n"
                + "instanceIds=" + instanceIds + "\n"
                + "productCategory=" + productCategory + "\n"
                + "serverEventType=" + serverEventType + "\n"
                + "serverEventLogTimeFilter=" + serverEventLogTimeFilter + "\n"
                + "periodStartTime=" + periodStartTime + "\n"
                + "periodEndTime=" + periodEndTime + "\n"
                + "serverEventStatus=" + serverEventStatus + "\n"
                + "}";
    }

    @Override
    public DescribeServerEventReq withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}