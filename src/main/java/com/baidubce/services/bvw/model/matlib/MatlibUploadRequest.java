/*
 * Copyright 2019-2020 Baidu, Inc.
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
package com.baidubce.services.bvw.model.matlib;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request of uploading media to material library.
 */
public class MatlibUploadRequest  extends AbstractBceRequest {

    /**
     * The media type，video/audio/image.
     */
    private String mediaType;
    /**
     * The material title.
     */
    private String title;
    /**
     * The source media in bos bucket.
     */
    private String bucket;
    /**
     * The source media in bos key.
     */
    private String key;
    /**
     * The notification name which to callback when process of uploading media is completed;
     * if none, will not callback.
     */
    private String notification;

    public MatlibUploadRequest() {
    }

    public MatlibUploadRequest(String mediaType, String title, String bucket, String key) {
        this.mediaType = mediaType;
        this.title = title;
        this.bucket = bucket;
        this.key = key;
    }

    public MatlibUploadRequest(String mediaType, String title, String bucket, String key, String notification) {
        this.mediaType = mediaType;
        this.title = title;
        this.bucket = bucket;
        this.key = key;
        this.notification = notification;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    @Override
    public String toString() {
        return "MatlibUploadRequest{" +
                "mediaType='" + mediaType + '\'' +
                ", title='" + title + '\'' +
                ", bucket='" + bucket + '\'' +
                ", key='" + key + '\'' +
                ", notification='" + notification + '\'' +
                '}';
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
