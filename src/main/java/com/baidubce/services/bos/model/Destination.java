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

/**
 * The config of dest replication for bucket replication
 */
public class Destination {

    /**
     * The dest replication bucket name
     */
    private String bucket;

    /**
     * The dest bucket's object storageclass
     */
    private String storageClass;

    /**
     * Gets the dest replication bucket name
     * @return
     */
    public String getBucket() {
        return bucket;
    }

    /**
     * Sets the dest replication bucket name
     * @param bucket
     */
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    /**
     * Gets the dest bucket's object storageclass
     * @return
     */
    public String getStorageClass() {
        return storageClass;
    }

    /**
     * Sets the dest bucket's object storageclass
     * @param storageClass
     */
    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

}
