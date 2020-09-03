/*
 * Copyright 2020 Baidu, Inc.
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
 *  Request for get Bucket's specific id Replication.
 */
public class GetBucketReplicationRequest  extends  GenericBucketRequest {

    /**
     * The replication id
     */
    private String id;

    /**
     * Gets the replication id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the replication id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Constructs a void Constructor for GetBucketReplicationRequest.
     */
    public GetBucketReplicationRequest() {

    }

    /**
     * Constructs a new GetBucketReplicationRequest to get a specified Bucket Replication.
     * @param bucketName
     */
    public GetBucketReplicationRequest(String bucketName) {
        super(bucketName);
    }

    /**
     * Constructs a new GetBucketReplicationRequest to get a specified Bucket Replication.
     * @param bucketName
     */
    public GetBucketReplicationRequest(String bucketName, String id) {
        super(bucketName);
        this.setId(id);
    }

    /**
     * Set the replication id
     * @param id
     * @return
     */
    public GetBucketReplicationRequest withId(String id) {
        this.setId(id);
        return this;
    }

    @Override
    public GetBucketReplicationRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public GetBucketReplicationRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
