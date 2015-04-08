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
 * Contains the details returned from Baidu Bos after calling the UploadPart
 * operation.
 */
public class UploadPartResponse {

    /**
     * The part number of the newly uploaded part
     */
    private int partNumber;

    /**
     * The entity tag generated from the content of the upload part
     */
    private String eTag;

    /**
     * Returns the part number of the newly uploaded part.
     *
     * @return The part number of the newly uploaded part.
     */
    public int getPartNumber() {
        return this.partNumber;
    }

    /**
     * Sets the part number of the newly uploaded part.
     *
     * @param partNumber the part number of the newly uploaded part.
     */
    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * Returns the entity tag of the newly uploaded part. The entity tag is
     * needed later when the multipart upload is completed.
     *
     * @return the entity tag of the newly uploaded part.
     */
    public String getETag() {
        return this.eTag;
    }

    /**
     * Sets the entity tag of the newly uploaded part.
     *
     * @param eTag the entity tag of the newly uploaded part.
     */
    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    /**
     * Returns an identifier which identifies the upload part by its part number
     * and the entity tag computed from the part's data. This information is
     * later needed to complete a multipart upload.
     *
     * @return An identifier which identifies the upload part by its part number
     * and the entity tag computed from the part's data.
     */
    public PartETag getPartETag() {
        return new PartETag(this.partNumber, this.eTag);
    }
}
