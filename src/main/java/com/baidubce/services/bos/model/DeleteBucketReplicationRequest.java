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

import com.baidubce.auth.BceCredentials;

/**
 * Delete Bucket Replication Request
 */
public class DeleteBucketReplicationRequest extends GenericBucketRequest {

    /**
     * The replication rule name
     */
    private String id;


    /**
     * Constructs a void Constructor for DeleteBucketReplicationRequest.
     */
    public DeleteBucketReplicationRequest() {
    }

    /**
     * Constructs a new DeleteBucketReplicationRequest, ready to be executed to delete the specified Bucket Replication.
     * @param bucketName The name of the Baidu Bos bucket to delete Bucket Replication.
     */
    public DeleteBucketReplicationRequest(String bucketName) {
        super(bucketName);
    }

    /**
     * Constructs a new DeleteBucketReplicationRequest, ready to be executed to delete the specified Bucket Replication.
     * @param bucketName The name of the Baidu Bos bucket to delete Bucket Replication.
     * @param id   replication rule name
     */
    public DeleteBucketReplicationRequest(String bucketName, String id) {
        super(bucketName);
        this.setId(id);
    }

    /**
     * Sets the name of the Baidu Bos bucket to delete Bucket Replication.
     * @param bucketName The name of the Baidu Bos bucket to delete Bucket Replication.
     */
    @Override
    public DeleteBucketReplicationRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public DeleteBucketReplicationRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
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

}
