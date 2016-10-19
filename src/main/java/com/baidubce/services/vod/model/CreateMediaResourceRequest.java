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
package com.baidubce.services.vod.model;

import java.io.File;
import java.io.InputStream;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bos.model.ObjectMetadata;

/**
 * Uploads a new object to the specified Baidu VOD bucket. The PutObjectRequest optionally uploads object metadata and
 * applies a canned access control policy to the new object.
 *
 * <p>
 * Baidu VOD never stores partial objects; if during this call an exception wasn't thrown, the entire object was stored.
 */
public class CreateMediaResourceRequest extends AbstractBceRequest {

    /**
     * The file containing the data to be uploaded to Baidu VOD. You must either specify a file or an InputStream
     * containing the data to be uploaded to Baidu Bos.
     */
    private File file;

    /**
     * The InputStream containing the data to be uploaded to Baidu Bos. You must either specify a file or an InputStream
     * containing the data to be uploaded to Baidu Bos.
     */
    private InputStream inputStream;

    /**
     * Optional metadata instructing Baidu Bos how to handle the uploaded data (e.g. custom user metadata, hooks for
     * specifying content type, etc.). If you are uploading from an InputStream, you <bold>should always</bold> specify
     * metadata with the content size set, otherwise the contents of the InputStream will have to be buffered in memory
     * before they can be sent to Baidu Bos, which can have very negative performance impacts.
     */
    private ObjectMetadata objectMetadata = new ObjectMetadata();

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Gets the path and name of the file containing the data to be uploaded to Baidu Bos. Either specify a file or an
     * input stream containing the data to be uploaded to Baidu Bos; both cannot be specified.
     *
     * @return The path and name of the file containing the data to be uploaded to Baidu Bos.
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Sets the path and name of the file containing the data to be uploaded to Baidu Bos. Either specify a file or an
     * input stream containing the data to be uploaded to Baidu Bos; both cannot be specified.
     *
     * @param file The path and name of the file containing the data to be uploaded to Baidu Bos.
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Sets the file containing the data to be uploaded to Baidu Bos. Returns this PutObjectRequest, enabling additional
     * method calls to be chained together.
     *
     * <p>
     * Either specify a file or an input stream containing the data to be uploaded to Baidu Bos; both cannot be
     * specified.
     *
     * @param file The file containing the data to be uploaded to Baidu Bos.
     * @return This PutObjectRequest, enabling additional method calls to be chained together.
     */
    public CreateMediaResourceRequest withFile(File file) {
        this.setFile(file);
        return this;
    }

    /**
     * Gets the optional metadata instructing Baidu Bos how to handle the uploaded data (e.g. custom user metadata,
     * hooks for specifying content type, etc.).
     *
     * <p>
     * If uploading from an input stream, <b>always</b> specify metadata with the content size set. Otherwise the
     * contents of the input stream have to be buffered in memory before being sent to Baidu Bos. This can cause very
     * negative performance impacts.
     *
     * @return The optional metadata instructing Baidu Bos how to handle the uploaded data (e.g. custom user metadata,
     *         hooks for specifying content type, etc.).
     */
    public ObjectMetadata getObjectMetadata() {
        return this.objectMetadata;
    }

    /**
     * Sets the optional metadata instructing Baidu Bos how to handle the uploaded data (e.g. custom user metadata,
     * hooks for specifying content type, etc.).
     *
     * <p>
     * If uploading from an input stream, <b>always</b> specify metadata with the content size set. Otherwise the
     * contents of the input stream have to be buffered in memory before being sent to Baidu Bos. This can cause very
     * negative performance impacts.
     *
     * @param objectMetadata The optional metadata instructing Baidu Bos how to handle the uploaded data (e.g. custom
     *            user metadata, hooks for specifying content type, etc.).
     */
    public void setObjectMetadata(ObjectMetadata objectMetadata) {
        this.objectMetadata = objectMetadata;
    }

    /**
     * Sets the optional metadata instructing Baidu Bos how to handle the uploaded data (e.g. custom user metadata,
     * hooks for specifying content type, etc.). Returns this PutObjectRequest, enabling additional method calls to be
     * chained together.
     *
     * <p>
     * If uploading from an input stream, <b>always</b> specify metadata with the content size set. Otherwise the
     * contents of the input stream have to be buffered in memory before being sent to Baidu Bos. This can cause very
     * negative performance impacts.
     *
     * @param objectMetadata The optional metadata instructing Baidu Bos how to handle the uploaded data (e.g. custom
     *            user metadata, hooks for specifying content type, etc.).
     * @return This PutObjectRequest, enabling additional method calls to be chained together.
     */
    public CreateMediaResourceRequest withObjectMetadata(ObjectMetadata objectMetadata) {
        this.setObjectMetadata(objectMetadata);
        return this;
    }

    /**
     * Gets the input stream containing the data to be uploaded to Baidu Bos. The user of this request must either
     * specify a file or an input stream containing the data to be uploaded to Baidu Bos; both cannot be specified.
     *
     * @return The input stream containing the data to be uploaded to Baidu Bos. Either specify a file or an input
     *         stream containing the data to be uploaded to Baidu Bos, not both.
     */
    public InputStream getInputStream() {
        return this.inputStream;
    }

    /**
     * Sets the input stream containing the data to be uploaded to Baidu Bos. Either specify a file or an input stream
     * containing the data to be uploaded to Baidu Bos; both cannot be specified.
     *
     * @param inputStream The input stream containing the data to be uploaded to Baidu Bos. Either specify a file or an
     *            input stream containing the data to be uploaded to Baidu Bos, not both.
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Sets the input stream containing the data to be uploaded to Baidu Bos. Returns this PutObjectRequest, enabling
     * additional method calls to be chained together.
     *
     * <p>
     * Either specify a file or an input stream containing the data to be uploaded to Baidu Bos; both cannot be
     * specified.
     *
     * @param inputStream The InputStream containing the data to be uploaded to Baidu Bos.
     * @return This PutObjectRequest, so that additional method calls can be chained together.
     */
    public CreateMediaResourceRequest withInputStream(InputStream inputStream) {
        this.setInputStream(inputStream);
        return this;
    }
}
