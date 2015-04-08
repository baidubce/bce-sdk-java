/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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

import java.io.Closeable;
import java.io.IOException;

import com.baidubce.services.bos.BosObjectInputStream;

/**
 * Represents an object stored in Baidu Bos. This object contains the data content
 * and the object metadata stored by Baidu Bos, such as content type, content length, etc.
 */
public class BosObject implements Closeable {

    /**
     * The name of the bucket in which this object is contained.
     */
    private String bucketName = null;

    /**
     * The key under which this object is stored.
     */
    private String key = null;

    /**
     * The metadata stored by Baidu Bos for this object.
     */
    private ObjectMetadata objectMetadata = new ObjectMetadata();

    /**
     * The stream containing the contents of this object from Bos.
     */
    private BosObjectInputStream objectContent;

    /**
     * Gets the name of the bucket in which this object is contained.
     *
     * @return The name of the bucket in which this object is contained.
     */
    public String getBucketName() {
        return this.bucketName;
    }

    /**
     * Sets the name of the bucket in which this object is contained.
     *
     * @param bucketName The name of the bucket containing this object.
     */
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * Gets the key under which this object is stored.
     *
     * @return The key under which this object is stored.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Sets the key under which this object is stored.
     *
     * @param key The key under which this object is stored.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the metadata stored by Bos for this object. The ObjectMetadata object includes any custom user metadata
     * supplied by the caller when the object was uploaded, as well as HTTP metadata such as content length and content type.
     *
     * @return The metadata stored by Baidu Bos for this object.
     */
    public ObjectMetadata getObjectMetadata() {
        return this.objectMetadata;
    }

    /**
     * Sets the object metadata for this object.
     *
     * @param objectMetadata The new metadata to set for this object in memory.
     */
    public void setObjectMetadata(ObjectMetadata objectMetadata) {
        this.objectMetadata = objectMetadata;
    }

    /**
     * Gets an input stream containing the contents of this object. Callers should close this input stream
     * as soon as possible, because the object contents aren't buffered in memory and stream directly from Baidu Bos.
     *
     * @return An input stream containing the contents of this object.
     */
    public BosObjectInputStream getObjectContent() {
        return this.objectContent;
    }

    /**
     * Sets the input stream containing this object's contents.
     *
     * @param objectContent The input stream containing this object's contents.
     */
    public void setObjectContent(BosObjectInputStream objectContent) {
        this.objectContent = objectContent;
    }

    @Override
    public String toString() {
        return "BosObject [bucketName=" + this.bucketName + ", key="
                + this.key + ", metadata=" + this.objectMetadata + "]";
    }

    @Override
    public void close() throws IOException {
        if (this.getObjectContent() != null) {
            this.getObjectContent().close();
        }
    }
}
