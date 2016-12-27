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

import static com.google.common.base.Preconditions.checkNotNull;

import com.baidubce.model.AbstractBceRequest;

public abstract class GenericBucketRequest extends AbstractBceRequest {
    private static final int MIN_BUCKET_NAME_LENGTH = 3;
    private static final int MAX_BUCKET_NAME_LENGTH = 63;

    private String bucketName;

    public GenericBucketRequest() {

    }

    public GenericBucketRequest(String bucketName) {
        this.setBucketName(bucketName);
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        checkNotNull(bucketName, "bucketName should not be null.");
        bucketName = bucketName.trim();
        if (bucketName.length() < MIN_BUCKET_NAME_LENGTH) {
            throw new IllegalArgumentException("Invalid bucketName:" + bucketName + ". " +
                    "bucketName should not be less than " + MIN_BUCKET_NAME_LENGTH +
                    ".");
        }
        if (bucketName.length() > MAX_BUCKET_NAME_LENGTH) {
            throw new IllegalArgumentException("Invalid bucketName:" + bucketName + ". " +
                    "bucketName should not be greater than " + MAX_BUCKET_NAME_LENGTH +
                    ".");
        }
        if (!isLowercaseOrDigit(bucketName.charAt(0))) {
            throw new IllegalArgumentException("Invalid bucketName:" + bucketName + ". " +
                    "bucketName should start with a lowercase letter or digit.");
        }
        if (!isLowercaseOrDigit(bucketName.charAt(bucketName.length() - 1))) {
            throw new IllegalArgumentException("Invalid bucketName:" + bucketName + ". " +
                    "bucketName should end with a lowercase letter or digit.");
        }
        for (int i = 1; i < bucketName.length() - 1; ++i) {
            char ch = bucketName.charAt(i);
            if (!isLowercaseOrDigit(ch) && ch != '-') {
                throw new IllegalArgumentException("Invalid bucketName:" + bucketName + ". " +
                        "bucketName should contain only lowercase leters, " +
                        "digits and hyphens(-).");
            }
        }
        this.bucketName = bucketName;
    }

    public abstract GenericBucketRequest withBucketName(String bucketName);

    private static boolean isLowercaseOrDigit(char ch) {
        return Character.isDigit(ch) || ch >= 'a' && ch <= 'z';
    }

}
