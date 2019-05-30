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

import java.util.List;

/**
 *  Provides options for deleting a multiple objects.
 */
public class DeleteMultipleObjectsRequest extends GenericObjectRequest {

    /**
     * the object key list excepted to be deleted.
     */
    private List<String> objectKeys;

    /**
     * the jsonDeleteObjects excepted to be deleted.
     */
    private String jsonDeleteObjects;

    /**
     * Gets the jsonDeleteObjects expected to be deleted.
     * @return json format for delete multiple objects.
     */
    public String getJsonDeleteObjects() {
        return jsonDeleteObjects;
    }

    /**
     * Sets the jsonDeleteObjects expected to be deleted.
     * @param jsonDeleteObjects The json format for delete multiple objects.
     */
    public void setJsonDeleteObjects(String jsonDeleteObjects) {
        this.jsonDeleteObjects = jsonDeleteObjects;
    }

    /**
     * Gets the object key list expected to be deleted.
     * @return the object key list expected to be deleted.
     */
    public List<String> getObjectKeys() {
        return objectKeys;
    }

    /**
     * Sets the object key list expected to be deleted.
     * @param objectKeys The object key list expected to be deleted.
     */
    public void setObjectKeys(List<String> objectKeys) {
        this.objectKeys = objectKeys;
    }


    @Override
    public DeleteMultipleObjectsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public DeleteMultipleObjectsRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public DeleteMultipleObjectsRequest withKey(String key) {
        this.setKey(key);
        return this;
    }

}