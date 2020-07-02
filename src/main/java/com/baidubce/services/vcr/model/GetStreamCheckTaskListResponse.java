/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
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

package com.baidubce.services.vcr.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Vcr get stream check task list response.
 */
public class GetStreamCheckTaskListResponse extends AbstractBceResponse {
    private Integer maxKeys;
    private String marker;
    private String nextMarker;
    private boolean isTruncated;
    private List<StreamCheckTask> tasks;

    public Integer getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getNextMarker() {
        return nextMarker;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    public boolean isTruncated() {
        return isTruncated;
    }

    public void setTruncated(boolean truncated) {
        isTruncated = truncated;
    }

    public List<StreamCheckTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<StreamCheckTask> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetStreamCheckTaskListResponse{");
        sb.append("maxKeys='").append(maxKeys).append('\'');
        sb.append("marker='").append(marker).append('\'');
        sb.append("nextMarker='").append(nextMarker).append('\'');
        sb.append("isTruncated='").append(isTruncated).append('\'');
        sb.append("tasks='").append(tasks).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
