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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.InputStream;

import com.baidubce.auth.BceCredentials;

/**
 * Contains the parameters used for the UploadPart operation on Baidu Bos.
 *
 * <p>
 * Required Parameters: BucketName, Key, UploadId, PartNumber
 */
public class UploadPartRequest extends GenericUploadRequest {

    /**
     * The part number describing this part's position relative to the other
     * parts in the multipart upload. Part number must be between 1 and 10,000
     * (inclusive).
     */
    private int partNumber;

    /**
     * The size of this part, in bytes.
     */
    private long partSize;

    /**
     * The optional, but recommended, MD5 hash of the content of this part. If
     * specified, this value will be sent to Baidu Bos to verify the data
     * integrity when the data reaches Baidu Bos.
     */
    private String md5Digest;

    /**
     * The stream containing the data to upload for the new part. Exactly one
     * File or InputStream must be specified as the input to this operation.
     */
    private InputStream inputStream;

    /**
     * The xBceCrc of the newly uploaded part
     */
    private String xBceCrc;

    /**
     * Gets xBceCrc of the newly uploaded part.
     * @return xBceCrc of the newly uploaded part.
     */
    public String getxBceCrc() {
        return xBceCrc;
    }

    /**
     * Sets xBceCrc of the newly uploaded part.
     * @param xBceCrc The xBceCrc of the newly uploaded part.
     */
    public void setxBceCrc(String xBceCrc) {
        this.xBceCrc = xBceCrc;
    }

    public UploadPartRequest() {
        super();
    }

    /**
     * Constructs a new UploadPartRequest object to upload a stream of data to the
     * specified bucket and key,
     *
     * @param bucketName The name of the bucket containing the initiated multipart upload with
     *     which this new part will be associated.
     * @param key The key of the initiated multipart upload.
     * @param uploadId The ID of an existing, initiated multipart upload, with which this new
     *     part will be associated.
     * @param partNumber The part number describing this part's position relative to the other
     *     parts in the multipart upload. Part number must be between 1 and 10,000 (inclusive).
     * @param partSize The size of this part, in bytes.
     * @param inputStream The stream containing the data to upload for the new part. Exactly one
     *     File or InputStream must be specified as the input to this operation.
     */
    public UploadPartRequest(String bucketName, String key, String uploadId, int partNumber, long partSize,
                             InputStream inputStream) {
        super(bucketName, key, uploadId);
        this.setPartNumber(partNumber);
        this.setPartSize(partSize);
        this.setInputStream(inputStream);
    }

    @Override
    public UploadPartRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Sets the name of the bucket containing the existing, initiated multipart
     * upload, with which this new part will be associated, and returns this
     * updated object so that additional method calls can be chained together.
     *
     * @param bucketName the name of the bucket containing the existing, initiated
     *     multipart upload, with which this new part will be associated.
     * @return This updated UploadPartRequest object.
     */
    @Override
    public UploadPartRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Sets the key of the initiated multipart upload, and returns this updated
     * object so that additional method calls can be chained together.
     *
     * @param key the key of the initiated multipart upload.
     * @return This updated UploadPartRequest object.
     */
    @Override
    public UploadPartRequest withKey(String key) {
        this.setKey(key);
        return this;
    }

    /**
     * Sets the ID of the existing, initiated multipart upload with which this
     * new part will be associated, and returns this updated UploadPartRequest
     * object so that additional method calls can be chained together.
     *
     * @param uploadId the ID of the existing, initiated multipart upload with which
     *    this new part will be associated.
     * @return This updated UploadPartRequest object.
     */
    @Override
    public UploadPartRequest withUploadId(String uploadId) {
        this.setUploadId(uploadId);
        return this;
    }

    /**
     * Returns the part number describing this part's position relative to the
     * other parts in the multipart upload. Part number must be between 1 and
     * 10,000 (inclusive).
     *
     * @return the part number describing this part's position relative to the
     *     other parts in the multipart upload. Part number must be between
     *     1 and 10,000 (inclusive).
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
     *     the other parts in the multipart upload. Part number must be
     *     between 1 and 10,000 (inclusive).
     */
    public void setPartNumber(int partNumber) {
        checkArgument(partNumber > 0, "partNumber should be positive, but is %s", partNumber);
        this.partNumber = partNumber;
    }

    /**
     * Sets the part number describing this part's position relative to the
     * other parts in the multipart upload. Part number must be between 1 and
     * 10,000 (inclusive).
     *
     * <p>
     * Returns this updated UploadPartRequest object so that additional method
     * calls can be chained together.
     *
     * @param partNumber the part number describing this part's position relative to
     *     the other parts in the multipart upload. Part number must be between 1 and 10,000 (inclusive).
     * @return This updated UploadPartRequest object.
     */
    public UploadPartRequest withPartNumber(int partNumber) {
        this.setPartNumber(partNumber);
        return this;
    }

    /**
     * Returns the size of this part, in bytes.
     *
     * @return the size of this part, in bytes.
     */
    public long getPartSize() {
        return this.partSize;
    }

    /**
     * Sets the size of this part, in bytes.
     *
     * @param partSize the size of this part, in bytes.
     */
    public void setPartSize(long partSize) {
        checkArgument(partSize >= 0, "partSize should not be negative.");
        this.partSize = partSize;
    }

    /**
     * Sets the size of this part, in bytes, and returns this updated
     * UploadPartRequest object so that additional method calls can be chained together.
     *
     * @param partSize the size of this part, in bytes.
     * @return This updated UploadPartRequest object.
     */
    public UploadPartRequest withPartSize(long partSize) {
        this.setPartSize(partSize);
        return this;
    }

    /**
     * Returns the optional, but recommended, MD5 hash of the content of this
     * part. If specified, this value will be sent to Baidu Bos to verify the
     * data integrity when the data reaches Baidu Bos.
     *
     * @return The optional, but recommended, MD5 hash of the content of this
     *     part. If specified, this value will be sent to Baidu Bos to
     *     verify the data integrity when the data reaches Baidu Bos.
     */
    public String getMd5Digest() {
        return this.md5Digest;
    }

    /**
     * Sets the optional, but recommended, MD5 hash of the content of this part.
     * If specified, this value will be sent to Baidu Bos to verify the data
     * integrity when the data reaches Baidu Bos.
     *
     * @param md5Digest The optional, but recommended, MD5 hash of the content of this
     *     part. If specified, this value will be sent to Baidu Bos to
     *     verify the data integrity when the data reaches Baidu Bos.
     */
    public void setMd5Digest(String md5Digest) {
        this.md5Digest = md5Digest;
    }

    /**
     * Sets the optional, but recommended, MD5 hash of the content of this part.
     * If specified, this value will be sent to Baidu Bos to verify the data
     * integrity when the data reaches Baidu Bos.
     *
     * <p>
     * Returns this updated UploadPartRequest object so that additional method
     * calls can be chained together.
     *
     * @param md5Digest The optional, but recommended, MD5 hash of the content of this
     *     part. If specified, this value will be sent to Baidu Bos to
     *     verify the data integrity when the data reaches Baidu Bos.
     * @return This updated UploadPartRequest object.
     */
    public UploadPartRequest withMD5Digest(String md5Digest) {
        this.setMd5Digest(md5Digest);
        return this;
    }

    /**
     * Returns the stream containing the data to upload for the new part.
     *
     * @return the stream containing the data to upload for the new part.
     */
    public InputStream getInputStream() {
        return this.inputStream;
    }

    /**
     * Sets the stream containing the data to upload for the new part.
     *
     * @param inputStream the stream containing the data to upload for the new part.
     */
    public void setInputStream(InputStream inputStream) {
        checkNotNull(inputStream, "inputStream should not be null.");
        this.inputStream = inputStream;
    }

    /**
     * Sets the stream containing the data to upload for the new part, and
     * returns this updated object so that additional method calls can be
     * chained together.
     *
     * @param inputStream the stream containing the data to upload for the new part.
     * @return The updated UploadPartRequest object.
     */
    public UploadPartRequest withInputStream(InputStream inputStream) {
        this.setInputStream(inputStream);
        return this;
    }
}
