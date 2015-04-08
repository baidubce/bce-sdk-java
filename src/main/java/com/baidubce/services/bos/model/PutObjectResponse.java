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

/**
 * Contains the data returned by Baidu Bos from the <code>putObject</code> operation.
 * Use this request to access information about the new object created from the
 * <code>putObject</code> request, such as its ETag and optional version ID.
 */
public class PutObjectResponse {

    /**
     * The ETag value of the new object
     */
    private String eTag;

    /**
     * Gets the ETag value for the newly created object.
     *
     * @return The ETag value for the new object.
     */
    public String getETag() {
        return this.eTag;
    }

    /**
     * Sets the ETag value for the new object that was created from the
     * associated <code>putObject</code> request.
     *
     * @param eTag The ETag value for the new object.
     */
    public void setETag(String eTag) {
        this.eTag = eTag;
    }

}