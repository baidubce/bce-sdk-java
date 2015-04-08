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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Container for the parameters of the CompleteMultipartUpload operation.
 *
 * <p>
 * Required Parameters: BucketName, Key, UploadId, ObjectMetadata, PartETags
 */
public class CompleteMultipartUploadRequest extends GenericUploadRequest {
    /**
     * Optional metadata instructing Baidu Bos how to handle the uploaded data
     * (e.g. custom user metadata, hooks for specifying content type, etc.).
     */
    private ObjectMetadata objectMetadata = new ObjectMetadata();

    /**
     * The list of part numbers and ETags to use when completing the multipart upload.
     */
    private List<PartETag> partETags;

    public CompleteMultipartUploadRequest() {
        super();
    }

    /**
     * Constructs a new request to complete a multipart upload.
     *
     * @param bucketName The name of the bucket containing the multipart upload to complete.
     * @param key The key of the multipart upload to complete.
     * @param uploadId The ID of the multipart upload to complete.
     * @param partETags The list of part numbers and ETags to use when completing the multipart upload.
     */
    public CompleteMultipartUploadRequest(String bucketName, String key, String uploadId, List<PartETag> partETags) {
        this(bucketName, key, uploadId, partETags, null);
    }

    /**
     * Constructs a new request to complete a multipart upload.
     *
     * @param bucketName The name of the bucket containing the multipart upload to complete.
     * @param key The key of the multipart upload to complete.
     * @param uploadId The ID of the multipart upload to complete.
     * @param partETags The list of part numbers and ETags to use when completing the multipart upload.
     * @param objectMetadata The optional metadata instructing Baidu Bos how to handle the uploaded data.
     */
    public CompleteMultipartUploadRequest(String bucketName, String key, String uploadId, List<PartETag> partETags,
                                          ObjectMetadata objectMetadata) {
        super(bucketName, key, uploadId);
        this.partETags = partETags;
        this.objectMetadata = objectMetadata;
    }

    @Override
    public CompleteMultipartUploadRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Sets the name of the bucket containing the multipart upload to complete,
     * and returns this updated CompleteMultipartUploadRequest so that
     * additional method calls can be chained together.
     *
     * @param bucketName The name of the bucket containing the multipart upload to complete.
     * @return The updated CompleteMultipartUploadRequest.
     */
    @Override
    public CompleteMultipartUploadRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Sets the key under which the multipart upload to complete is stored, and
     * returns this updated CompleteMultipartUploadRequest object so that
     * additional method calls can be chained together.
     *
     * @param key The key under which the multipart upload to complete is stored.
     * @return This updated CompleteMultipartUploadRequest object.
     */
    @Override
    public CompleteMultipartUploadRequest withKey(String key) {
        this.setKey(key);
        return this;
    }

    /**
     * Sets the ID of the multipart upload to complete, and returns this updated
     * CompleteMultipartUploadRequest object so that additional method calls can
     * be chained together.
     *
     * @param uploadId The ID of the multipart upload to complete.
     * @return This updated CompleteMultipartUploadRequest object.
     */
    @Override
    public CompleteMultipartUploadRequest withUploadId(String uploadId) {
        this.setUploadId(uploadId);
        return this;
    }

    /**
     * Gets the optional metadata instructing Baidu Bos how to handle the uploaded data.
     *
     * @return The optional metadata instructing Baidu Bos how to handle the uploaded data.
     */
    public ObjectMetadata getObjectMetadata() {
        return this.objectMetadata;
    }

    /**
     * Sets the optional metadata instructing Baidu Bos how to handle the uploaded data.
     *
     * @param objectMetadata The optional metadata instructing Baidu Bos how to handle the uploaded data.
     */
    public void setObjectMetadata(ObjectMetadata objectMetadata) {
        this.objectMetadata = objectMetadata;
    }

    /**
     * Sets the optional metadata instructing Baidu Bos how to handle the uploaded data .
     * Returns this CompleteMultipartUploadRequest, enabling additional method calls to be chained together.
     *
     * @param objectMetadata The optional metadata instructing Baidu Bos how to handle the uploaded data.
     * @return Returns this CompleteMultipartUploadRequest, enabling additional method calls to be chained together.
     */
    public CompleteMultipartUploadRequest withObjectMetadata(ObjectMetadata objectMetadata) {
        this.setObjectMetadata(objectMetadata);
        return this;
    }

    /**
     * Returns the list of part numbers and ETags that identify the individual
     * parts of the multipart upload to complete.
     *
     * @return The list of part numbers and ETags that identify the individual
     * parts of the multipart upload to complete.
     */
    public List<PartETag> getPartETags() {
        return this.partETags;
    }

    /**
     * Sets the list of part numbers and ETags that identify the individual
     * parts of the multipart upload to complete.
     *
     * @param partETags The list of part numbers and ETags that identify the
     *     individual parts of the multipart upload to complete.
     */
    public void setPartETags(List<PartETag> partETags) {
        checkNotNull(partETags, "partETags should not be null.");
        for (int i = 0; i < partETags.size(); ++i) {
            PartETag partETag = partETags.get(i);
            checkNotNull(partETag, "partETags[%s] should not be null.", i);
            int partNumber = partETag.getPartNumber();
            checkArgument(partNumber > 0, "partNumber should be positive. partETags[%s].partNumber:%s", i, partNumber);
            checkNotNull(partETag.getETag(), "partETags[%s].eTag should not be null.", i);
        }
        Collections.sort(partETags, new Comparator<PartETag>() {
            @Override
            public int compare(PartETag tag1, PartETag tag2) {
                return tag1.getPartNumber() - tag2.getPartNumber();
            }
        });
        int lastPartNumber = 0;
        for (int i = 0; i < partETags.size(); ++i) {
            int partNumber = partETags.get(i).getPartNumber();
            checkArgument(partNumber != lastPartNumber, "Duplicated partNumber %s.", partNumber);
            lastPartNumber = partNumber;
        }
        this.partETags = partETags;
    }

    /**
     * Sets the list of part numbers and ETags that identify the individual
     * parts of the multipart upload to complete, and returns this updated
     * CompleteMultipartUploadRequest object so that additional method calls can be chained.
     *
     * @param partETags The list of part numbers and ETags that identify the
     *     individual parts of the multipart upload to complete.
     * @return This updated CompleteMultipartUploadRequest object.
     */
    public CompleteMultipartUploadRequest withPartETags(List<PartETag> partETags) {
        this.setPartETags(partETags);
        return this;
    }
}
