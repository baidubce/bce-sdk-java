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

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Container for summary information about a part in a multipart upload, such as
 * part number, size, etc.
 */
public class PartSummary {

    /**
     * The part number describing this part's position relative to the other
     * parts in the multipart upload. Part number must be between 1 and 10,000
     * (inclusive).
     */
    private int partNumber;

    /**
     * The date at which this part was last modified.
     */
    private Date lastModified;

    /**
     * The entity tag generated from the part content.
     */
    private String eTag;

    /**
     * The size, in bytes, of the part.
     */
    private long size;

    /**
     * Returns the part number describing this part's position relative to the
     * other parts in the multipart upload. Part number must be between 1 and
     * 10,000 (inclusive).
     *
     * @return the part number describing this part's position relative to the
     * other parts in the multipart upload. Part number must be between
     * 1 and 10,000 (inclusive).
     */
    public int getPartNumber() {
        return this.partNumber;
    }

    /**
     * Sets the part number describing this part's position relative to the
     * other parts in the multipart upload. Part number must be between 1 and
     * 10,000 (inclusive).
     *
     * @param partNumber the part number describing this part's position relative to
     *                   the other parts in the multipart upload. Part number must be
     *                   between 1 and 10,000 (inclusive).
     */
    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * Returns the date this part was last modified.
     *
     * @return the date this part was last modified.
     */
    public Date getLastModified() {
        return this.lastModified;
    }

    /**
     * Sets the date this part was last modified.
     *
     * @param lastModified the date this part was last modified.
     */
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Returns the entity tag generated from the part content.
     *
     * @return the entity tag generated from the part content.
     */
    public String getETag() {
        return this.eTag;
    }

    /**
     * Sets the entity tag generated from the part content.
     *
     * @param eTag the entity tag generated from the part content.
     */
    @JsonProperty("eTag")
    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    /**
     * Returns the size of this part, in bytes.
     *
     * @return the size of this part, in bytes.
     */
    public long getSize() {
        return this.size;
    }

    /**
     * Sets the size of this part, in bytes.
     *
     * @param size the size of this part, in bytes.
     */
    public void setSize(long size) {
        this.size = size;
    }

}
