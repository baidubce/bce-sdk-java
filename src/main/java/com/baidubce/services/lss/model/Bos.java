/*
 * Copyright 2015 Baidu, Inc.
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

package com.baidubce.services.lss.model;

/**
 * Represents the BOS information
 */
public class Bos {
    private String bucket = null;

    private String region = null;

    /**
     * Returns the bucket name.
     *
     * @return the bucket name
     */
    public String getBucket() {
        return bucket;
    }

    /**
     * Sets the bucket name.
     *
     * @param bucket the bucket name
     */
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    /**
     * Sets the bucket name.
     *
     * @param bucket the bucket name
     * @return this object
     */
    public Bos withBucket(String bucket) {
        this.bucket = bucket;
        return this;
    }

    /**
     * Returns the region ID.
     *
     * @return the region ID
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the region ID.
     *
     * @param region the region ID
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Sets the region ID
     *
     * @param region the region ID
     * @return this object
     */
    public Bos withRegion(String region) {
        this.region = region;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class Bos {\n");
        sb.append("    bucket: ").append(bucket).append("\n");
        sb.append("    region: ").append(region).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
