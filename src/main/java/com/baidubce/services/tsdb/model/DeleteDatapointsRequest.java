/*
 * Copyright 2018 Baidu, Inc.
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
package com.baidubce.services.tsdb.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;
import java.util.Map;

/**
 * Represent the request for deleting datapoints.
 */
public class DeleteDatapointsRequest extends AbstractBceRequest {

    private String databaseId;

    /**
     * Option.
     * Conflict with metricFieldsList.
     */
    private List<String> metrics;

    /**
     * Option.
     * Conflict with metrics.
     */
    private List<MetricFields> metricFieldsList;

    private Long start;

    private Long end;

    private Map<String, List<TaskTagFilter>> tags;

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }

    public List<String> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<String> metrics) {
        this.metrics = metrics;
    }

    public List<MetricFields> getMetricFieldsList() {
        return metricFieldsList;
    }

    public void setMetricFieldsList(List<MetricFields> metricFieldsList) {
        this.metricFieldsList = metricFieldsList;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public Map<String, List<TaskTagFilter>> getTags() {
        return tags;
    }

    public void setTags(Map<String, List<TaskTagFilter>> tags) {
        this.tags = tags;
    }

    @Override
    public DeleteDatapointsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
