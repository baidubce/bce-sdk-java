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

import java.util.List;

import com.baidubce.model.User;
import com.google.common.collect.Lists;

/**
 * Response object for listing all the Baidu Bos buckets owned by a user.
 */
public class ListBucketsResponse extends BosResponse {

    /**
     * The owner of this bucket.
     */
    private User owner;

    /**
     * The list of buckets owned by a user.
     */
    private List<BucketSummary> buckets = Lists.newArrayList();

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<BucketSummary> getBuckets() {
        return this.buckets;
    }

    public void setBuckets(List<BucketSummary> buckets) {
        this.buckets = buckets;
    }

}
