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

import com.baidubce.auth.BceCredentials;

/**
 * Container for the the parameters of the ListParts operation.
 *
 * <p>
 * Required Parameters: BucketName, Key, UploadId
 */
public class ListPartsRequest extends GenericUploadRequest {

    /**
     * The optional maximum number of parts to be returned in the part listing.
     */
    private int maxParts = -1;

    /**
     * The optional part number marker indicating where in the results to being listing parts.
     */
    private int partNumberMarker;

    /**
     * Constructs a new ListPartsRequest from the required parameters bucket
     * name, key and upload ID.
     *
     * @param bucketName The name of the bucket containing the parts to list.
     * @param key The key of the associated multipart upload whose parts are being listed.
     * @param uploadId The ID of the multipart upload whose parts are being listed.
     */
    public ListPartsRequest(String bucketName, String key, String uploadId) {
        super(bucketName, key, uploadId);
    }

    @Override
    public ListPartsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Sets the BucketName property for this request.
     *
     * @param bucketName The value that BucketName is set to.
     * @return the request with the BucketName set.
     */
    @Override
    public ListPartsRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Sets the key of the associated multipart upload whose parts are being
     * listed, and returns this updated ListPartsRequest object so that
     * additional method calls can be chained together.
     *
     * @param key The key of the associated multipart upload whose parts are being listed.
     * @return This updated ListPartsRequest object.
     */
    @Override
    public ListPartsRequest withKey(String key) {
        this.setKey(key);
        return this;
    }

    /**
     * Sets the ID of the multipart upload whose parts are being listed, and
     * returns this updated ListPartsRequest object so that additional method
     * calls can be chained together.
     *
     * @param uploadId The ID of the multipart upload whose parts are being listed.
     * @return This updated ListPartsRequest object.
     */
    @Override
    public ListPartsRequest withUploadId(String uploadId) {
        this.setUploadId(uploadId);
        return this;
    }

    /**
     * Returns the optional maximum number of parts to be returned in the part listing.
     *
     * @return The optional maximum number of parts to be returned in the part listing.
     */
    public int getMaxParts() {
        return this.maxParts;
    }

    /**
     * Sets the optional maximum number of parts to be returned in the part listing.
     *
     * @param maxParts The optional maximum number of parts to be returned in the part listing.
     */
    public void setMaxParts(int maxParts) {
        this.maxParts = maxParts;
    }

    /**
     * Sets the optional maximum number of parts to be returned in the part
     * listing and returns this updated ListPartsRequest objects so that
     * additional method calls can be chained together.
     *
     * @param maxParts The optional maximum number of parts to be returned in the part listing.
     * @return This updated ListPartsRequest object.
     */
    public ListPartsRequest withMaxParts(int maxParts) {
        this.maxParts = maxParts;
        return this;
    }

    /**
     * Returns the optional part number marker indicating where in the results to being listing parts.
     *
     * @return The optional part number marker indicating where in the results to being listing parts.
     */
    public int getPartNumberMarker() {
        return this.partNumberMarker;
    }

    /**
     * Sets the optional part number marker indicating where in the results to being listing parts.
     *
     * @param partNumberMarker The optional part number marker indicating where in the results to being listing parts.
     */
    public void setPartNumberMarker(int partNumberMarker) {
        this.partNumberMarker = partNumberMarker;
    }

    /**
     * Sets the optional part number marker indicating where in the results to
     * being listing parts, and returns this updated ListPartsRequest object so
     * that additional method calls can be chained together.
     *
     * @param partNumberMarker The optional part number marker indicating where in the results to being listing parts.
     * @return This updated ListPartsRequest object.
     */
    public ListPartsRequest withPartNumberMarker(int partNumberMarker) {
        this.setPartNumberMarker(partNumberMarker);
        return this;
    }

}
