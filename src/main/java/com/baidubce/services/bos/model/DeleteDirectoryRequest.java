/*
 * Copyright 2014 Baidu, Inc.
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
 * Used in BOS Namespace
 */
public class DeleteDirectoryRequest extends GenericObjectRequest {

    boolean isDeleteRecursive = false;
    String deleteMarker;

    public DeleteDirectoryRequest(String bucketName, String key) {
        super(bucketName, key);
    }

    public DeleteDirectoryRequest(String bucketName, String key, boolean deleteRecursive) {
        super(bucketName, key);
        this.isDeleteRecursive = deleteRecursive;
    }

    public DeleteDirectoryRequest(String bucketName, String key, boolean deleteRecursive, String deleteMarker) {
        super(bucketName, key);
        this.deleteMarker = deleteMarker;
    }

    @Override
    public DeleteDirectoryRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public DeleteDirectoryRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public DeleteDirectoryRequest withKey(String key) {
        this.setKey(key);
        return this;
    }

    public boolean isDeleteRecursive() {
        return isDeleteRecursive;
    }

    public void setDeleteRecursive(boolean deleteRecursive) {
        isDeleteRecursive = deleteRecursive;
    }

    public DeleteDirectoryRequest withDeleteRecursive(boolean deleteRecursive) {
        this.setDeleteRecursive(deleteRecursive);
        return this;
    }

    public String getDeleteMarker() {
        return deleteMarker;
    }

    public void setDeleteMarker(String deleteMarker) {
        this.deleteMarker = deleteMarker;
    }

    public DeleteDirectoryRequest withDeleteToken(String deleteMarker) {
        this.setDeleteMarker(deleteMarker);
        return this;
    }
}