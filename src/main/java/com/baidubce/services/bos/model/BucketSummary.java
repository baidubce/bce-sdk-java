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

import java.util.Date;

/**
 * Represents an Baidu Bos bucket.
 *
 * <p>
 * Every object stored in Baidu Bos is contained within a bucket. Buckets partition the namespace of objects stored
 * in Baidu Bos at the top level. Within a bucket, any name can be used for objects. However, bucket names must be
 * unique across all of Baidu Bos.
 *
 * <p>
 * There are no limits to the number of objects that can be stored in a bucket. Performance does not vary based on
 * the number of buckets used. Store all objects within a single bucket or organize them across several buckets.
 */
public class BucketSummary {

    /**
     * The name of this Baidu Bos bucket.
     */
    private String name = null;

    /**
     * The date this bucket was created.
     */
    private Date creationDate = null;

    /**
     * Constructs a bucket without any name specified.
     */
    public BucketSummary() {
    }

    /**
     * Creates a bucket with a name.
     * All buckets in Baidu Bos share a single namespace; ensure the bucket is given a unique name.
     *
     * @param name The name for the bucket.
     */
    public BucketSummary(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the bucket.
     *
     * @return The name of this bucket.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the bucket. All buckets in Baidu Bos share a single namespace;
     * ensure the bucket is given a unique name.
     *
     * @param name The name for the bucket.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the bucket's creation date. Returns <code>null</code> if the creation date is not known.
     *
     * @return The bucket's creation date, or <code>null</code> if not known.
     */
    public Date getCreationDate() {
        return this.creationDate;
    }

    /**
     * For internal use only. Sets the bucket's creation date in Bos.
     *
     * @param creationDate The bucket's creation date.
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Bucket [name=" + this.name + ", creationDate=" + this.creationDate + "]";
    }

}
