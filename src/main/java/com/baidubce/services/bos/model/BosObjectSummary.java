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

import com.baidubce.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

/**
 * Contains the summary of an object stored in a Baidu Bos bucket. This object doesn't contain contain the
 * object's full metadata or any of its contents.
 */
public class BosObjectSummary {

    /**
     * The name of the bucket in which this object is stored.
     */
    protected String bucketName;

    /**
     * The key under which this object is stored.
     */
    protected String key;

    /**
     * Hex encoded MD5 hash of this object's contents, as computed by Baidu Bos.
     */
    protected String eTag;

    /**
     * The size of this object, in bytes.
     */
    protected long size;

    /**
     * The date, according to Baidu Bos, when this object was last modified.
     */
    protected Date lastModified;

    /**
     * The user of this object - can be null if the requester doesn't have permission to view object ownership information.
     */
    protected User owner;

    
    /**
     * The storage class of object.
     */
    protected String storageClass;

    protected Map<String, String> userMeta;


    /**
     * Gets the name of the Baidu Bos bucket in which this object is stored.
     *
     * @return The name of the Baidu Bos bucket in which this object is stored.
     */
    public String getBucketName() {
        return this.bucketName;
    }

    /**
     * Sets the name of the Baidu Bos bucket in which this object is stored.
     *
     * @param bucketName The name of the Baidu Bos bucket in which this object is stored.
     */
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * Gets the key under which this object is stored in Baidu Bos.
     *
     * @return The key under which this object is stored in Baidu Bos.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Sets the key under which this object is stored in Baidu Bos.
     *
     * @param key The key under which this object is stored in Baidu Bos.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the hex encoded 128-bit MD5 hash of this object's contents as computed by Baidu Bos.
     *
     * @return The hex encoded 128-bit MD5 hash of this object's contents as computed by Baidu Bos.
     */
    public String getETag() {
        return this.eTag;
    }

    /**
     * Sets the hex encoded 128-bit MD5 hash of this object's contents as computed by Baidu Bos.
     *
     * @param eTag The hex encoded 128-bit MD5 hash of this object's contents as computed by Baidu Bos.
     */
    @JsonProperty("eTag")
    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    /**
     * Gets the size of this object in bytes.
     *
     * @return The size of this object in bytes.
     */
    public long getSize() {
        return this.size;
    }

    /**
     * Sets the size of this object in bytes.
     *
     * @param size The size of this object in bytes.
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     * Gets the date when, according to Baidu Bos, this object was last modified.
     *
     * @return The date when, according to Baidu Bos, this object was last modified.
     */
    public Date getLastModified() {
        return this.lastModified;
    }

    /**
     * Sets the date, according to Baidu Bos, this object was last modified.
     *
     * @param lastModified The date when, according to Baidu Bos, this object was last modified.
     */
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Gets the owner of this object.
     *
     * @return The owner of this object.
     */
    public User getOwner() {
        return this.owner;
    }

    /**
     * Sets the owner of this object.
     *
     * @param owner The owner of this object.
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Gets the storageClass of this object.
     *
     * @return The storageClass of this object.
     */
    public String getStorageClass() {
        return this.storageClass;
    }

    /**
     * Sets the storageClass of this object.
     *
     * @param storageClass The storageClass of this object.
     */
    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    public Map<String, String> getUserMeta() {
        return userMeta;
    }

    public void setUserMeta(Map<String, String> userMeta) {
        this.userMeta = userMeta;
    }

    @Override
    public String toString() {
        return "BosObjectSummary [\n  bucketName=" + bucketName + ", \n  key=" + key
                + ", \n  eTag=" + eTag + ", \n  size=" + size + ", \n  lastModified="
                + lastModified + ", \n  owner=" + owner + ", \n  storageClass="
                + storageClass + "\n]";
    }

}
