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
 * Uploads a new object to the specified Baidu Bos bucket. The PutObjectRequest optionally uploads object metadata
 * and applies a canned access control policy to the new object.
 *
 * <p>
 * Baidu Bos never stores partial objects; if during this call an exception wasn't thrown, the entire object was stored.
 */
public class PutObjectRequest extends GenericObjectRequest {

    /**
     * The file containing the data to be uploaded to Baidu Bos. You must either
     * specify a file or an InputStream containing the data to be uploaded to
     * Baidu Bos.
     */
    private File file;

    /**
     * The InputStream containing the data to be uploaded to Baidu Bos. You must
     * either specify a file or an InputStream containing the data to be
     * uploaded to Baidu Bos.
     */
    private InputStream inputStream;

    /**
     * Optional metadata instructing Baidu Bos how to handle the uploaded data
     * (e.g. custom user metadata, hooks for specifying content type, etc.). If
     * you are uploading from an InputStream, you <bold>should always</bold>
     * specify metadata with the content size set, otherwise the contents of the
     * InputStream will have to be buffered in memory before they can be sent to
     * Baidu Bos, which can have very negative performance impacts.
     */
    private ObjectMetadata objectMetadata = new ObjectMetadata();

    /**
     * The StorageClass is an identification that distinguish between infrequent access bos
     * and standard bos. 
     */
    private String storageClass;

    /**
     * Used to Audio and video processing interface
     */
    private String videoProcess = null;

    /**
     * The BosProgressCallback used for get progress information
     */
    private BosProgressCallback progressCallback = null;

    /**
     * Constructs a new PutObjectRequest object to upload a file to the
     * specified bucket and key. After constructing the request,
     * users may optionally specify object metadata or a canned ACL as well.
     *
     * @param bucketName The name of an existing bucket to which the new object will be uploaded.
     * @param key The key under which to store the new object.
     * @param file The path of the file to upload to Baidu Bos.
     */
    public PutObjectRequest(String bucketName, String key, File file) {
        this(bucketName, key, file, null, new ObjectMetadata());
        checkNotNull(file, "file should not be null.");
    }

    /**
     * Constructs a new PutObjectRequest object to upload a file to the
     * specified bucket and key. After constructing the request,
     * users may optionally specify object metadata or a canned ACL as well.
     *
     * @param bucketName The name of an existing bucket to which the new object will be uploaded.
     * @param key The key under which to store the new object.
     * @param file The path of the file to upload to Baidu Bos.
     * @param metadata The object metadata. At minimum this specifies the
     *     content length for the stream of data being uploaded.
     */
    public PutObjectRequest(String bucketName, String key, File file, ObjectMetadata metadata) {
        this(bucketName, key, file, null, metadata);
        checkNotNull(file, "file should not be null.");
        checkNotNull(metadata, "metadata should not be null.");
    }

    /**
     * Constructs a new PutObjectRequest object to upload a stream of data to
     * the specified bucket and key. After constructing the request,
     * users may optionally specify object metadata or a canned ACL as well.
     *
     * @param bucketName The name of an existing bucket to which the new object will be uploaded.
     * @param key The key under which to store the new object.
     * @param inputStream The stream of data to upload to Baidu Bos.
     */
    public PutObjectRequest(String bucketName, String key, InputStream inputStream) {
        this(bucketName, key, null, inputStream, new ObjectMetadata());
        checkNotNull(inputStream, "inputStream should not be null.");
    }

    /**
     * Constructs a new PutObjectRequest object to upload a stream of data to
     * the specified bucket and key. After constructing the request,
     * users may optionally specify object metadata or a canned ACL as well.
     *
     * @param bucketName The name of an existing bucket to which the new object will be uploaded.
     * @param key The key under which to store the new object.
     * @param inputStream The stream of data to upload to Baidu Bos.
     * @param progressCallback The BosProgressCallback, which used for get progress information.
     */
    public PutObjectRequest(String bucketName, String key, InputStream inputStream,
                            BosProgressCallback progressCallback) {
        this(bucketName, key, null, inputStream, new ObjectMetadata());
        checkNotNull(inputStream, "inputStream should not be null.");
        this.setProgressCallback(progressCallback);
    }

    /**
     * Constructs a new PutObjectRequest object to upload a stream of data to
     * the specified bucket and key. After constructing the request,
     * users may optionally specify object metadata or a canned ACL as well.
     *
     * @param bucketName The name of an existing bucket to which the new object will be uploaded.
     * @param key The key under which to store the new object.
     * @param inputStream The stream of data to upload to Baidu Bos.
     * @param metadata The object metadata. At minimum this specifies the
     *     content length for the stream of data being uploaded.
     */
    public PutObjectRequest(String bucketName, String key, InputStream inputStream, ObjectMetadata metadata) {
        this(bucketName, key, null, inputStream, metadata);
        checkNotNull(inputStream, "inputStream should not be null.");
        checkNotNull(metadata, "metadata should not be null.");
    }

    /**
     * Constructs a new PutObjectRequest object to upload a file and a stream of data to
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
    protected PutObjectRequest(String bucketName, String key, File file, InputStream inputStream,
                             ObjectMetadata objectMetadata) {
        super(bucketName, key);
        this.file = file;
        this.inputStream = inputStream;
        this.objectMetadata = objectMetadata;
    }

    @Override
    public PutObjectRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Sets the name of the bucket where this request will upload a new
     * object to. Returns this object, enabling additional method calls to be
     * chained together.
     *
     * @param bucketName The name of an existing bucket where this request will upload a new object to.
     * @return This PutObjectRequest, enabling additional method calls to be chained together.
     */
    @Override
    public PutObjectRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Sets the key under which to store the new object. Returns this object,
     * enabling additional method calls to be chained together.
     *
     * @param key The key under which to store the new object.
     * @return This PutObjectRequest, enabling additional method calls to be chained together.
     */
    @Override
    public PutObjectRequest withKey(String key) {
        this.setKey(key);
        return this;
    }

    /**
     * Gets the path and name of the file containing the data to be uploaded to Baidu Bos.
     * Either specify a file or an input stream containing the data to be
     * uploaded to Baidu Bos; both cannot be specified.
     *
     * @return The path and name of the file containing the data to be uploaded to Baidu Bos.
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Sets the path and name of the file
     * containing the data to be uploaded to Baidu Bos.
     * Either specify a file or an input stream containing the data to be
     * uploaded to Baidu Bos; both cannot be specified.
     *
     * @param file The path and name of the  file containing the data to be uploaded to Baidu Bos.
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Sets the file containing the data to be uploaded to Baidu Bos.
     * Returns this PutObjectRequest, enabling additional method calls to be chained together.
     *
     * <p>
     * Either specify a file or an input stream containing the data to
     * be uploaded to Baidu Bos; both cannot be specified.
     *
     * @param file The file containing the data to be uploaded to Baidu Bos.
     * @return This PutObjectRequest, enabling additional method calls to be chained together.
     */
    public PutObjectRequest withFile(File file) {
        this.setFile(file);
        return this;
    }

    /**
     * Gets the optional metadata instructing Baidu Bos how to handle the
     * uploaded data (e.g. custom user metadata, hooks for specifying content
     * type, etc.).
     *
     * <p>
     * If uploading from an input stream,
     * <b>always</b> specify metadata with the content size set. Otherwise the
     * contents of the input stream have to be buffered in memory before
     * being sent to Baidu Bos. This can cause very negative performance
     * impacts.
     *
     * @return The optional metadata instructing Baidu Bos how to handle the
     *     uploaded data (e.g. custom user metadata, hooks for specifying content type, etc.).
     */
    public ObjectMetadata getObjectMetadata() {
        return this.objectMetadata;
    }

    /**
     * Sets the optional metadata instructing Baidu Bos how to handle the
     * uploaded data (e.g. custom user metadata, hooks for specifying content
     * type, etc.).
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
     */
    public void setObjectMetadata(ObjectMetadata objectMetadata) {
        this.objectMetadata = objectMetadata;
    }

    /**
     * Sets the optional metadata instructing Baidu Bos how to handle the
     * uploaded data (e.g. custom user metadata, hooks for specifying content
     * type, etc.). Returns this PutObjectRequest, enabling additional method
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
     * @return This PutObjectRequest, enabling additional method calls to be chained together.
     */
    public PutObjectRequest withObjectMetadata(ObjectMetadata objectMetadata) {
        this.setObjectMetadata(objectMetadata);
        return this;
    }

    /**
     * Gets the input stream containing the data to be uploaded to Baidu Bos.
     * The user of this request
     * must either specify a file or an input stream containing the data to be
     * uploaded to Baidu Bos; both cannot be specified.
     *
     * @return The input stream containing the data to be uploaded to Baidu Bos.
     *     Either specify a file or an input stream containing the
     *     data to be uploaded to Baidu Bos, not both.
     */
    public InputStream getInputStream() {
        return this.inputStream;
    }

    /**
     * Sets the input stream containing the data to be uploaded to Baidu Bos.
     * Either specify a file or an input stream containing the data to be
     * uploaded to Baidu Bos; both cannot be specified.
     *
     * @param inputStream The input stream containing the data to be uploaded to Baidu Bos.
     *     Either specify a file or an input stream containing the data to be uploaded to Baidu Bos, not both.
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Sets the input stream containing the data to be uploaded to Baidu Bos.
     * Returns this PutObjectRequest, enabling additional method calls to be chained together.
     *
     * <p>
     * Either specify a file or an input stream containing the data to be
     * uploaded to Baidu Bos; both cannot be specified.
     *
     * @param inputStream The InputStream containing the data to be uploaded to Baidu Bos.
     * @return This PutObjectRequest, so that additional method calls can be chained together.
     */
    public PutObjectRequest withInputStream(InputStream inputStream) {
        this.setInputStream(inputStream);
        return this;
    }
    /**
     * Gets the storageClass of the input file which is be uploaded to Baidu Bos.
     * 
     * @return storageClass  The StorageClass is an identification that distinguish between infrequent access bos
     * and standard bos.
     */
    public String getStorageClass() {
        return storageClass;
    }

    /**
     * Sets the storageClass of the input file which is be uploaded to Baidu Bos.
     * 
     * @param storageClass  The StorageClass is an identification that distinguish between infrequent access bos 
     * and standard bos.
     */
    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    /**
     * Sets the storageClass of the input file which is be uploaded to Baidu Bos.
     * 
     * @param storageClass  The StorageClass is an identification that distinguish between infrequent access bos 
     * and standard bos.
     * @return This PutObjectRequest, so that additional method calls can be chained together.
     */
    public PutObjectRequest withStorageClass(String storageClass) {
        this.setStorageClass(storageClass);
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
     * @return This PutObjectRequest, so that additional method calls can be chained together
     */
    public PutObjectRequest withProgressCallback(BosProgressCallback progressCallback) {
        this.setProgressCallback(progressCallback);
        return this;
    }

    /**
     * Gets the limit of put object speed.
     * @return the limit of put object speed. unit: bit/s
     */
    public long getTrafficLimitBitPS() {
        return trafficLimitBitPS;
    }

    /**
     * Sets Gets the limit of put object speed. range: 819200 bit/s ~ 838860800 bit/s
     * @param trafficLimitBitPS the limit of put object speed. unit: bit/s
     */
    public void setTrafficLimitBitPS(long trafficLimitBitPS) {
        this.trafficLimitBitPS = trafficLimitBitPS;
    }

    /**
     *
     * @param trafficLimitBitPS the limit of put object speed. unit: bit/s, range: 819200 bit/s ~ 838860800 bit/s
     * @return This PutObjectRequest, so that additional method calls can be chained together
     */
    public PutObjectRequest withTrafficLimitBitPS(long trafficLimitBitPS) {
        this.setTrafficLimitBitPS(trafficLimitBitPS);
        return this;
    }

    /**
     * Gets the parameter of Audio and video processing
     * @return the parameter of Audio and video processing
     */
    public String getVideoProcess() {
        return videoProcess;
    }

    /**
     *
     * @param videoProcess The parameter of Audio and video processing
     * @return This PutObjectRequest, so that additional method calls can be chained together
     */
    public void setVideoProcess(String videoProcess) {
        this.videoProcess = videoProcess;
    }

    /**
     *
     * @param videoProcess The parameter of Audio and video processing
     * @return This PutObjectRequest, so that additional method calls can be chained together
     */
    public PutObjectRequest withVideoProcess(String videoProcess) {
        this.setVideoProcess(videoProcess);
        return this;
    }
}
