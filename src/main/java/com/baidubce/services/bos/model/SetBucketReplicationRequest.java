/*
 * Copyright 2014-2019 Baidu, Inc.
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

import com.baidubce.auth.BceCredentials;

/**
 * Request object containing all the options for setting a bucket's replication.
 */
public class SetBucketReplicationRequest extends  GenericBucketRequest {

    /**
     * The json format for Bucket Replication.
     */
    private String jsonBucketReplication;

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
     * The replication rule name
     */
    private String id;

    /**
     * Constructs a void Constructor for SetBucketReplicationRequest.
     */
    public SetBucketReplicationRequest() {
    }

    /**
     * Constructs a new SetBucketReplicationRequest to get a specified Bucket Lifecycle.
     * @param bucketName
     */
    public SetBucketReplicationRequest(String bucketName) {
        super(bucketName);
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
     * Sets the status bucket replication
     * and returns this object, enabling additional method calls
     * to be chained together.
     *
     * @param status  The status bucket replication.
     * @return This <code>SetBucketReplicationRequest</code>, enabling additional method calls to be  chained together.
     */
    public SetBucketReplicationRequest withStatus(String status) {
        this.setStatus(status);
        return this;
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
     * Gets json formot bucket replication
     * @return
     */
    public String getJsonBucketReplication() {
        return jsonBucketReplication;
    }

    /**
     * Sets json formot bucket replication
     * @param jsonBucketReplication
     */
    public void setJsonBucketReplication(String jsonBucketReplication) {
        this.jsonBucketReplication = jsonBucketReplication;
    }

    /**
     * Sets json formot bucket replication
     * @param jsonBucketReplication json formot bucket replication
     * @return This <code>SetBucketReplicationRequest</code>, enabling additional method calls to be  chained together.
     */
    public SetBucketReplicationRequest withJsonBucketReplication(String jsonBucketReplication) {
        this.setJsonBucketReplication(jsonBucketReplication);
        return this;
    }

    /**
     * Sets the replication prefix
     * @param resource the replication prefix
     * @return This <code>SetBucketReplicationRequest</code>, enabling additional method calls to be  chained together.
     */
    public SetBucketReplicationRequest withResource(String[] resource) {
        this.setResource(resource);
        return this;
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
     * Sets the config of dest replication
     * @param destination the config of dest replication
     * @return This <code>SetBucketReplicationRequest</code>, enabling additional method calls to be  chained together.
     */
    public SetBucketReplicationRequest withDestination(Destination destination) {
        this.setDestination(destination);
        return this;
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
    public void setReplicateHistory(
            ReplicateHistory replicateHistory) {
        this.replicateHistory = replicateHistory;
    }

    /**
     * Sets the history replication
     * @param replicateHistory the history replication
     * @return This <code>SetBucketReplicationRequest</code>, enabling additional method calls to be  chained together.
     */
    public SetBucketReplicationRequest withReplicationHistory(ReplicateHistory replicateHistory) {
        this.setReplicateHistory(replicateHistory);
        return this;
    }

    /**
     * Gets the status of sync delete
     * @return
     */
    public String getReplicateDeletes() {
        return replicateDeletes;
    }

    /**
     * Sets the status of sync delete
     * @param replicateDeletes
     */
    public void setReplicateDeletes(String replicateDeletes) {
        this.replicateDeletes = replicateDeletes;
    }

    /**
     * Sets the status of sync delete
     * @param replicateDeletes the status of sync delete
     * @return This <code>SetBucketReplicationRequest</code>, enabling additional method calls to be  chained together.
     */
    public SetBucketReplicationRequest withReplicationDeletes(String replicateDeletes) {
        this.setReplicateDeletes(replicateDeletes);
        return this;
    }
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
     * Sets the id of Bucket Replication.
     * @param id The id of Bucket Replication.
     * @return this object.
     */
    public SetBucketReplicationRequest withId(String id) {
        this.setId(id);
        return this;
    }

    @Override
    public SetBucketReplicationRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public SetBucketReplicationRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }


}
