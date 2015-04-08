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

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class GenericObjectRequest extends GenericBucketRequest {
    private static final int MIN_OBJECT_KEY_LENGTH = 0;
    private static final int MAX_OBJECT_KEY_LENGTH = 1024;

    private String key;

    public GenericObjectRequest() {
        super();
    }

    public GenericObjectRequest(String bucketName, String key) {
        super(bucketName);
        this.setKey(key);
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        checkNotNull(key, "key should not be null.");
        if (key.length() < MIN_OBJECT_KEY_LENGTH) {
            throw new IllegalArgumentException("Invalid objectKey:" + key + ". " +
                    "objectKey should not be less than " + MIN_OBJECT_KEY_LENGTH + ".");
        }
        if (key.length() > MAX_OBJECT_KEY_LENGTH) {
            throw new IllegalArgumentException("Invalid objectKey:" + key + ". " +
                    "objectKey should not be greater than " + MAX_OBJECT_KEY_LENGTH + ".");
        }
        this.key = key;
    }

    public abstract GenericObjectRequest withKey(String key);
}
