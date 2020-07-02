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
package com.baidubce.services.bvw.model.media;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.Map;

/**
 * Process media request.
 */
public class MediaProcessRequest extends AbstractBceRequest {

    /**
     * The media id.
     */
    private String mediaId;
    /**
     * The source bucket name.
     */
    private String sourceBucket;
    /**
     * The source object key.
     */
    private String sourceKey;
    /**
     * The media title.
     */
    private String title;
    /**
     * The media description.
     */
    private String description;
    /**
     * The using workflow name.
     */
    private String workflowName;
    /**
     * The dynamic params in this process.
     */
    private String dynamicParams;
    /**
     * The dynamic params map in this process.
     */
    private Map<String, String> dynamicParamsMap;

    /**
     * Construct a process media request with specified parameters.
     *
     * @param sourceBucket  The source bucket name
     * @param sourceKey     The source object key
     * @param title         The media title
     * @param description   The media description
     * @param workflowName  The using workflow name
     * @param dynamicParams The dynamic in this process
     * @return A processing media request
     */
    public static MediaProcessRequest of(String sourceBucket, String sourceKey, String title, String description,
                                         String workflowName, String dynamicParams) {
        MediaProcessRequest processRequest = new MediaProcessRequest();
        processRequest.setSourceBucket(sourceBucket);
        processRequest.setSourceKey(sourceKey);
        processRequest.setTitle(title);
        processRequest.setDescription(description);
        processRequest.setWorkflowName(workflowName);
        processRequest.setDynamicParams(dynamicParams);
        return processRequest;
    }

    /**
     * Construct a process media request with specified parameters.
     *
     * @param sourceBucket The source bucket name
     * @param sourceKey    The source object key
     * @param title        The media title
     * @param description  The media description
     * @param workflowName The using workflow name
     * @return A processing media request
     */
    public static MediaProcessRequest of(String sourceBucket, String sourceKey, String title, String description,
                                         String workflowName) {
        return of(sourceBucket, sourceKey, title, description, workflowName, null);
    }

    /**
     * Construct a process media request with specified parameters.
     *
     * @param mediaId       The media id
     * @param workflowName  The using workflow name
     * @param dynamicParams The dynamic in this process
     * @return A processing media request
     */
    public static MediaProcessRequest of(String mediaId, String workflowName, String dynamicParams) {
        MediaProcessRequest processRequest = new MediaProcessRequest();
        processRequest.setMediaId(mediaId);
        processRequest.setWorkflowName(workflowName);
        processRequest.setDynamicParams(dynamicParams);
        return processRequest;
    }

    /**
     * Construct a process media request with specified parameters.
     *
     * @param mediaId           The media id
     * @param workflowName      The using workflow name
     * @param dynamicParamsMap  The dynamic in this process
     * @return A processing media request
     */
    public static MediaProcessRequest ofDynamicMap(
            String mediaId, String workflowName, Map<String, String> dynamicParamsMap) {
        MediaProcessRequest processRequest = new MediaProcessRequest();
        processRequest.setMediaId(mediaId);
        processRequest.setWorkflowName(workflowName);
        processRequest.setDynamicParamsMap(dynamicParamsMap);
        return processRequest;
    }

    /**
     *Construct a process media request with specified parameters.
     *
     * @param sourceBucket      The source bucket name
     * @param sourceKey         The source object key
     * @param title             The media title
     * @param description       The media description
     * @param workflowName      The using workflow name
     * @param dynamicParamsMap  The dynamic in this process
     * @return A processing media request
     */
    public static MediaProcessRequest ofDynamicMap(
            String sourceBucket, String sourceKey, String title, String description, String workflowName,
            Map<String, String> dynamicParamsMap) {
        MediaProcessRequest processRequest = new MediaProcessRequest();
        processRequest.setSourceBucket(sourceBucket);
        processRequest.setSourceKey(sourceKey);
        processRequest.setTitle(title);
        processRequest.setDescription(description);
        processRequest.setWorkflowName(workflowName);
        processRequest.setDynamicParamsMap(dynamicParamsMap);
        return processRequest;
    }

    /**
     * Construct a process media request with specified parameters.
     *
     * @param mediaId      The media id
     * @param workflowName The using workflow name
     * @return A processing media request
     */
    public static MediaProcessRequest of(String mediaId, String workflowName) {
        return of(mediaId, workflowName, null);
    }

    @Override
    public MediaProcessRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getSourceBucket() {
        return sourceBucket;
    }

    public void setSourceBucket(String sourceBucket) {
        this.sourceBucket = sourceBucket;
    }

    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public String getDynamicParams() {
        return dynamicParams;
    }

    public void setDynamicParams(String dynamicParams) {
        this.dynamicParams = dynamicParams;
    }


    public Map<String, String> getDynamicParamsMap() {
        return dynamicParamsMap;
    }

    public void setDynamicParamsMap(Map<String, String> dynamicParamsMap) {
        this.dynamicParamsMap = dynamicParamsMap;
    }

}
