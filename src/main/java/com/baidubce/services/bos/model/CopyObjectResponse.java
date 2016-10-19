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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contains the data returned by Baidu Bos from the
 * {@link com.baidubce.services.bos.BosClient#copyObject(CopyObjectRequest copyObjectRequest)} call.
 * This result may be ignored if not needed; otherwise, use this result
 * to access information about the new object created from the copyObject call.
 */
public class CopyObjectResponse extends BosResponse {

    /**
     * The ETag value of the copied object.
     */
    private String eTag;

    /**
     * The last modified date for the copied object.
     */
    private Date lastModified;

    /**
     * Gets the ETag value for the new object that was created in the associated CopyObjectRequest.
     *
     * @return The ETag value for the new object.
     */
    public String getETag() {
        return this.eTag;
    }

    /**
     * Sets the ETag value for the new object that was created from the associated copy object request.
     *
     * @param eTag The ETag value for the new object.
     */
    @JsonProperty("eTag")
    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    /**
     * Gets the date the newly copied object was last modified.
     *
     * @return The date the newly copied object was last modified.
     */
    public Date getLastModified() {
        return this.lastModified;
    }

    /**
     * Sets the date the newly copied object was last modified.
     *
     * @param lastModified The date the new, copied object was last modified.
     */
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
