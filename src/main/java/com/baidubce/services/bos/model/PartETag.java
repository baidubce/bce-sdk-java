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

/**
 * Container for the part number and ETag of an uploaded part. After the part is
 * uploaded to Baidu Bos, this data is used when completing the multipart upload.
 */
public class PartETag {

    /**
     * The part number of the associated part.
     */
    private int partNumber;

    /**
     * The entity tag generated from the content of the associated part.
     */
    private String eTag;

    public PartETag() {
    }

    /**
     * Constructs an instance of PartETag and sets the part number and ETag.
     *
     * @param partNumber The part number.
     * @param eTag       the associated ETag for the part number.
     */
    public PartETag(int partNumber, String eTag) {
        this.setPartNumber(partNumber);
        this.setETag(eTag);
    }

    /**
     * Returns the part number of the associated part.
     *
     * @return the part number of the associated part.
     */
    public int getPartNumber() {
        return this.partNumber;
    }

    /**
     * Sets the part number of the associated part.
     *
     * @param partNumber the part number of the associated part.
     */
    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * Sets the part number of the associated part, and returns this updated
     * PartETag object so that additional method calls can be chained together.
     *
     * @param partNumber the part number of the associated part.
     * @return This updated PartETag object.
     */
    public PartETag withPartNumber(int partNumber) {
        this.setPartNumber(partNumber);
        return this;
    }

    /**
     * Returns the entity tag generated from the content of the associated part.
     *
     * @return the entity tag generated from the content of the associated part.
     */
    public String getETag() {
        return this.eTag;
    }

    /**
     * Sets the entity tag generated from the content of the associated part.
     *
     * @param eTag the entity tag generated from the content of the associated
     *             part.
     */
    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    /**
     * Sets the entity tag generated from the content of the associated part,
     * and returns this updated PartETag object so that additional method calls
     * can be chained together.
     *
     * @param eTag the entity tag generated from the content of the associated
     *             part.
     * @return This updated PartETag object.
     */
    public PartETag withETag(String eTag) {
        this.setETag(eTag);
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.eTag == null) ? 0 : this.eTag.hashCode());
        result = prime * result + this.partNumber;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        PartETag other = (PartETag) obj;
        if (this.eTag == null) {
            if (other.eTag != null) {
                return false;
            }
        } else if (!this.eTag.equals(other.eTag)) {
            return false;
        }
        if (this.partNumber != other.partNumber) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PartETag [partNumber=" + this.partNumber + ", eTag=" + this.eTag + "]";
    }
}
