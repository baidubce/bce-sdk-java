package com.baidubce.services.bos.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contains the details returned from Baidu Bos after calling the UploadPartCopy
 * operation.
 */
public class UploadPartCopyResponse extends BosResponse {

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
    @JsonProperty("eTag")
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