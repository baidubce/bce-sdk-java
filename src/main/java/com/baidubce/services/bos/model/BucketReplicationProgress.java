/*
 * Copyright 2014-2020 Baidu, Inc.
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
package com.baidubce.services.bos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bucket Replication Progress
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BucketReplicationProgress extends  BosResponse {

    /**
     * The flags of enable bucket replications, Options is enabled or disabled;
     */
    private String status;

    /**
     * The history replicate percent
     */
    private int historyReplicationPercent;

    /**
     * The latest replication Time
     */
    private long latestReplicationTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHistoryReplicationPercent() {
        return historyReplicationPercent;
    }

    public void setHistoryReplicationPercent(int historyReplicationPercent) {
        this.historyReplicationPercent = historyReplicationPercent;
    }

    public long getLatestReplicationTime() {
        return latestReplicationTime;
    }

    public void setLatestReplicationTime(long latestReplicationTime) {
        this.latestReplicationTime = latestReplicationTime;
    }

    @Override
    public String toString() {
        return "BucketReplicationProgress{" +
                "status='" + status + '\'' +
                ", historyReplicationPercent=" + historyReplicationPercent +
                ", latestReplicationTime=" + latestReplicationTime +
                '}';
    }

}
