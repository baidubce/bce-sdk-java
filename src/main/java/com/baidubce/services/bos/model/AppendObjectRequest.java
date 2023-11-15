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

import java.io.File;
import java.io.InputStream;

import com.baidubce.auth.BceCredentials;

/**
 * Uploads a new object to the specified Baidu Bos bucket. The AppendObjectRequest optionally uploads object metadata
 * and applies a canned access control policy to the new object.
 *
 * <p>
 * Baidu Bos never stores partial objects; if during this call an exception wasn't thrown, the entire object was stored.
 */
public class AppendObjectRequest extends PutObjectRequest {

    /**
     * The offset indicates the size of data has been uploaded to Baidu bos. If the value of the offset is null,
     * this means that users create a new appendable object on Baidu bos, otherwise it means that appending 
     * a object which had already exist.
     */
    private Long offset;

    /**
     * The BosProgressCallback used for get progress information
     */
    private BosProgressCallback progressCallback = null;

    /**
     * Constructs a new AppendObjectRequest object to upload a file to the
     * specified bucket and key. After constructing the request,
     * users may optionally specify object metadata or a canned ACL as well.
     *
     * @param bucketName The name of an existing bucket to which the new object will be uploaded.
     * @param key The key under which to store the new object.
     * @param file The path of the file to upload to Baidu Bos.
     */
    public AppendObjectRequest(String bucketName, String key, File file) {
        this(bucketName, key, file, null, new ObjectMetadata());
        checkNotNull(file, "file should not be null.");
    }

    /**
     * Constructs a new AppendObjectRequest object to upload an appendable file
     * to the specified bucket and key. After constructing the request,
     * users may optionally specify object metadata or a canned ACL as well.
     *
     * @param bucketName The name of an existing bucket to which the new object will be uploaded.
     * @param key The key under which to store the new object.
     * @param file The path of the file to upload to Baidu Bos.
     * @param metadata The object metadata. At minimum this specifies the
     *     content length for the stream of data being uploaded.
     */
    public AppendObjectRequest(String bucketName, String key, File file, ObjectMetadata metadata) {
        this(bucketName, key, file, null, metadata);
        checkNotNull(file, "file should not be null.");
        checkNotNull(metadata, "metadata should not be null.");
    }

    /**
     * Constructs a new AppendObjectRequest object to upload a stream of data to
     * the specified bucket and key. After constructing the request,
     * users may optionally specify object metadata or a canned ACL as well.
     *
     * @param bucketName The name of an existing bucket to which the new object will be uploaded.
     * @param key The key under which to store the new object.
     * @param inputStream The stream of data to upload to Baidu Bos.
     */
    public AppendObjectRequest(String bucketName, String key, InputStream inputStream) {
        this(bucketName, key, null, inputStream, new ObjectMetadata());
        checkNotNull(inputStream, "inputStream should not be null.");
    }

    /**
     * Constructs a new AppendObjectRequest object to upload a stream of data to
     * the specified bucket and key. After constructing the request,
     * users may optionally specify object metadata or a canned ACL as well.
     *
     * @param bucketName The name of an existing bucket to which the new object will be uploaded.
     * @param key The key under which to store the new object.
     * @param inputStream The stream of data to upload to Baidu Bos.
     * @param metadata The object metadata. At minimum this specifies the
     *     content length for the stream of data being uploaded.
     */
    public AppendObjectRequest(String bucketName, String key, InputStream inputStream, ObjectMetadata metadata) {
        this(bucketName, key, null, inputStream, metadata);
        checkNotNull(inputStream, "inputStream should not be null.");
        checkNotNull(metadata, "metadata should not be null.");
    }

    /**
     * Constructs a new AppendObjectRequest object to upload a file and a stream of data to
     * the specified bucket and key. After constructing the request,
     * users may optionally specify object metadata or a canned ACL as well.
     *
     * @param bucketName The name of an existing bucket to which the new object will be uploaded.
     * @param key The key under which to store the new object.
     * @param file The path of the file to upload to Baidu Bos.
     * @param inputStream The stream of data to upload to Baidu Bos.
     * @param objectMetadata The object metadata. At minimum this specifies the
     *     content length for the stream of data being uploaded.
     */
    private AppendObjectRequest(
            String bucketName, String key, File file, InputStream inputStream, ObjectMetadata objectMetadata) {
        super(bucketName, key, file, inputStream, objectMetadata);
    }

    /**
     * Gets the offset of the file, which shows the resume position of appendable file.
     * @return The offset that indicates the size of data has been uploaded to Baidu bos.
     */
    public Long getOffset() {
        return offset;
    }

    /**
     * Sets the offset of the file, which shows the resume position of appendable file.
     * @param offset The offset indicates the size of data has been uploaded to Baidu bos.
     */
    public void setOffset(Long offset) {
        this.offset = offset;
    }

    /**
     * Sets the offset of the file, which shows the resume position of appendable file.
     * @param offset The offset indicates the size of data has been uploaded to Baidu bos.
     * @return This AppendObjectRequest, so that additional method calls to be chained together.
     */
    public AppendObjectRequest withOffset(Long offset) {
        this.setOffset(offset);
        return this;
    }

    /**
     * Sets the optional metadata instructing Baidu Bos how to handle the
     * uploaded data (e.g. custom user metadata, hooks for specifying content
     * type, etc.). Returns this AppendObjectRequest, enabling additional method
     * calls to be chained together.
     *
     * <p>
     * If uploading from an input stream,
     * <b>always</b> specify metadata with the content size set. Otherwise the
     * contents of the input stream have to be buffered in memory before
     * being sent to Baidu Bos. This can cause very negative performance
     * impacts.
     *
     * @param objectMetadata The optional metadata instructing Baidu Bos how to handle the
     *     uploaded data (e.g. custom user metadata, hooks for specifying content type, etc.).
     * @return This AppendObjectRequest, so that additional method calls to be chained together.
     */
    @Override
    public AppendObjectRequest withObjectMetadata(ObjectMetadata objectMetadata) {
        this.setObjectMetadata(objectMetadata);
        return this;
    }

    /**
     * Sets the file containing the data to be uploaded to Baidu Bos.
     * Returns this AppendObjectRequest, enabling additional method calls to be chained together.
     *
     * <p>
     * Either specify a file or an input stream containing the data to
     * be uploaded to Baidu Bos; both cannot be specified.
     *
     * @param file The file containing the data to be uploaded to Baidu Bos.
     * @return This AppendObjectRequest, enabling additional method calls to be chained together.
     */
    @Override
    public AppendObjectRequest withFile(File file) {
        this.setFile(file);
        return this;
    }

    /**
     * Sets the input stream containing the data to be uploaded to Baidu Bos.
     * Returns this AppendObjectRequest, enabling additional method calls to be chained together.
     *
     * <p>
     * Either specify a file or an input stream containing the data to be
     * uploaded to Baidu Bos; both cannot be specified.
     *
     * @param inputStream The InputStream containing the data to be uploaded to Baidu Bos.
     * @return This AppendObjectRequest, so that additional method calls can be chained together.
     */
    @Override
    public AppendObjectRequest withInputStream(InputStream inputStream) {
        this.setInputStream(inputStream);
        return this;
    }

    /**
     * Sets the optional credentials to use for this request, overriding the default credentials set at the client
     * level.
     *
     * @param credentials The optional BOS security credentials to use for this request.
     * @return This AppendObjectRequest, so that additional method calls can be chained together.
     */
    @Override
    public AppendObjectRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Sets the name of the bucket where this request will upload a new
     * object to. Returns this object, enabling additional method calls to be
     * chained together.
     *
     * @param bucketName The name of an existing bucket where this request will upload a new object to.
     * @return This AppendObjectRequest, enabling additional method calls to be chained together.
     */
    @Override
    public AppendObjectRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Sets the key under which to store the new object. Returns this object,
     * enabling additional method calls to be chained together.
     *
     * @param key The key under which to store the new object.
     * @return This AppendObjectRequest, so that additional method calls to be chained together.
     */
    @Override
    public AppendObjectRequest withKey(String key) {
        this.setKey(key);
        return this;
    }

    /**
     * Gets the BosProgressCallback which used for Get upload progress.
     * @return The BosProgressCallback which used for get progress information.
     */
    public BosProgressCallback getProgressCallback() {
        return progressCallback;
    }

    /**
     * Sets the BosProgressCallback which used for Get upload progress.
     * @param progressCallback The BosProgressCallback, which used for get progress information.
     */
    public void setProgressCallback(BosProgressCallback progressCallback) {
        this.progressCallback = progressCallback;
    }

    /**
     *
     * @param progressCallback The BosProgressCallback, which used for get progress information.
     * @return This AppendObjectRequest, so that additional method calls can be chained together
     */
    public AppendObjectRequest withProgressCallback(BosProgressCallback progressCallback) {
        this.setProgressCallback(progressCallback);
        return this;
    }
}
