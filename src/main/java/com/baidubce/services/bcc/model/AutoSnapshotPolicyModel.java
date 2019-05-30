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
package com.baidubce.services.bcc.model;

import java.util.Date;
import java.util.List;

/**
 * The model of autoSnapshotPolicy detail information
 */
public class AutoSnapshotPolicyModel {
    /**
     * id of the asp
     */
    private String id;

    /**
     * name of the asp
     */
    private String name;

    /**
     * Specifies the time point of creating an automatic snapshot .
     * The smallest unit is the hour.
     * There are 24 time points from 00:00 to 23:00.
     * The number of parameters is 0 to 23.
     * For example, 1 represents the time point at 01:00.
     * Multiple time points can be selected.
     * The parameter is a Json Array:[0, 1,... 23], at most 24 time points.
     */
    private List<Integer> timePoints;

    /**
     * Specify a duplicate date for the automatic snapshot.
     * Choose the date from Monday to Sunday when you need to create snapshots,
     * with a number of parameters ranging from 1 to 7,
     * such as: 1 for Monday. Multiple dates are allowed.
     * The parameter is a Json Array:[1, 2... 7].
     */
    private List<Integer> repeatWeekdays;

    /**
     * Specify the retention time of the automatic snapshot in days.
     * -1: Permanent preservation .
     * 1-65536: specified number of days.
     */
    private String retentionDays;

    /**
     * status of asp
     */
    private String status;

    /**
     * create time of asp
     */
    private Date createdTime;

    /**
     * updated time of asp
     */
    private Date updatedTime;

    /**
     * deleted time of asp
     */
    private Date deletedTime;

    /**
     * last execute time of asp
     */
    private Date lastExecuteTime;

    /**
     * related volume's number
     */
    private int volumeCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getTimePoints() {
        return timePoints;
    }

    public void setTimePoints(List<Integer> timePoints) {
        this.timePoints = timePoints;
    }

    public List<Integer> getRepeatWeekdays() {
        return repeatWeekdays;
    }

    public void setRepeatWeekdays(List<Integer> repeatWeekdays) {
        this.repeatWeekdays = repeatWeekdays;
    }

    public String getRetentionDays() {
        return retentionDays;
    }

    public void setRetentionDays(String retentionDays) {
        this.retentionDays = retentionDays;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Date getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(Date deletedTime) {
        this.deletedTime = deletedTime;
    }

    public Date getLastExecuteTime() {
        return lastExecuteTime;
    }

    public void setLastExecuteTime(Date lastExecuteTime) {
        this.lastExecuteTime = lastExecuteTime;
    }

    public int getVolumeCount() {
        return volumeCount;
    }

    public void setVolumeCount(int volumeCount) {
        this.volumeCount = volumeCount;
    }

    @Override
    public String toString() {
        return "AutoSnapshotPolicyModel{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", timePoints=" + timePoints
                + ", repeatWeekdays=" + repeatWeekdays
                + ", retentionDays='" + retentionDays
                + '\'' + ", status='" + status + '\''
                + ", createdTime=" + createdTime
                + ", updatedTime=" + updatedTime
                + ", deletedTime=" + deletedTime
                + ", lastExecuteTime=" + lastExecuteTime
                + ", volumeCount=" + volumeCount
                + '}';
    }
}
