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

import java.util.Arrays;

/**
 * Get Bucket Replication Response
 */
public class GetBucketReplicationResponse  extends BosResponse {

    /**
     * The replication rule name
     */
    private String id;

    /**
     * The status of replication
     */
    private String status;

    /**
     * The replication prefix, it must start with bucketName
     */
    private String[] resource;

    /**
     * The config of dest replication
     */
    private Destination destination;

    /**
     * The history replication
     */
    private ReplicateHistory replicateHistory;

    /**
     * Whether enable sync delete
     */
    private String replicateDeletes;

    /**
     * The replication create time
     */
    private long createTime;

    /**
     * The dest bucket region
     */
    private String destRegion;

    /**
     * Gets the replication rule name
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the replication rule name
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the status of replication
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of replication
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the replication prefix
     * @return
     */
    public String[] getResource() {
        return resource;
    }

    /**
     * Sets the replication prefix
     * @param resource
     */
    public void setResource(String[] resource) {
        this.resource = resource;
    }

    /**
     * Gets the config of dest replication
     * @return
     */
    public Destination getDestination() {
        return destination;
    }

    /**
     * Sets the config of dest replication
     * @param destination
     */
    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    /**
     * Gets the history replication
     * @return
     */
    public ReplicateHistory getReplicateHistory() {
        return replicateHistory;
    }

    /**
     * Sets the history replication
     * @param replicateHistory
     */
    public void setReplicateHistory(ReplicateHistory replicateHistory) {
        this.replicateHistory = replicateHistory;
    }

    /**
     * Gets the sync delete flags
     * @return
     */
    public String getReplicateDeletes() {
        return replicateDeletes;
    }

    /**
     * Sets the sync delete flags
     * @param replicateDeletes
     */
    public void setReplicateDeletes(String replicateDeletes) {
        this.replicateDeletes = replicateDeletes;
    }

    /**
     * Gets the replication create_time
     * @return
     */
    public long getCreateTime() {
        return createTime;
    }

    /**
     * Sets the replication create_time
     * @param createTime
     */
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    /**
     * Gets the dest bucket region
     * @return
     */
    public String getDestRegion() {
        return destRegion;
    }

    /**
     * Sets the dest bucket region
     * @param destRegion
     */
    public void setDestRegion(String destRegion) {
        this.destRegion = destRegion;
    }


    @Override
    public String toString() {
        return "GetBucketReplicationResponse{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", resource=" + Arrays.toString(resource) +
                ", destination=" + destination +
                ", replicateHistory=" + replicateHistory +
                ", replicateDeletes='" + replicateDeletes + '\'' +
                ", createTime=" + createTime +
                ", destRegion='" + destRegion + '\'' +
                '}';
    }
}
