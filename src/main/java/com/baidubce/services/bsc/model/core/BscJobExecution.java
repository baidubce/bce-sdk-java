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
package com.baidubce.services.bsc.model.core;

import java.util.Date;

/**
 * the model of bsc job execution information
 */
public class BscJobExecution {
    private Long id;

    private Long jobId;

    private String name;

    private Integer cu;

    private Date startAt;

    private Date endAt;

    private String status;

    private String appId;

    private Long runningTime;

    private Double cuPrice;

    private Double totalPrice;

    public Double getCuPrice() { return cuPrice; }

    public void setCuPrice(Double cuPrice) { this.cuPrice = cuPrice; }

    public Double getTotalPrice() { return totalPrice; }

    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Long runningTime) {
        this.runningTime = runningTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCu() {
        return cu;
    }

    public void setCu(Integer cu) {
        this.cu = cu;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status == null ? null : status.trim(); }

    public void setJobId(Long jobId) { this.jobId = jobId; }

    public Long getJobId() { return jobId; }

    public void setAppId (String appId) { this.appId = appId == null ? null : appId.trim(); }

    public String getAppId() { return appId; }
}
