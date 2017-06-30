/*
 * Copyright 2015 Baidu, Inc.
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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * InternalCreateMediaRequest class used to construct a internal createMedia request,
 * and send to API server.
 */
public class InternalCreateMediaRequest extends AbstractBceRequest {

    /*
     * The unique ID of media resource managed by VOD service.
     */
    @JsonIgnore
    private String mediaId;

    /*
     * The title string of media resource
     */
    private String title;

    /*
     * The description string of media resource
     */
    private String description;

    /*
     * The media extension string
     */
    private String sourceExtension;

    /*
     * The media transcoding preset group name
     */
    private String transcodingPresetGroupName;

    /*
     * The transcoding priority
     */
    private int priority;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("InternalCreateMediaRequest { \n");
        sb.append("  mediaId = ").append(mediaId).append("\n");
        sb.append("  title = ").append(title).append("\n");
        sb.append("  description = ").append(description).append("\n");
        sb.append("  sourceExtension = ").append(sourceExtension).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

    /**
     * get media id
     *
     * @return the media id
     */
    public String getMediaId() {
        return mediaId;
    }

    /**
     * set media id
     *
     * @param mediaId the media id
     */
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    /**
     * set the media id
     *
     * @param mediaId the media id
     * @return InternalCreateMediaRequest after set mediaId
     */
    public InternalCreateMediaRequest withMediaId(String mediaId) {
        this.mediaId = mediaId;
        return this;
    }

    /**
     * get media title
     *
     * @return the media title
     */
    public String getTitle() {
        return title;
    }

    /**
     * set media title
     *
     * @param title the media title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * set the media title
     *
     * @param title the media title
     * @return InternalCreateMediaRequest after set title
     */
    public InternalCreateMediaRequest withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * get media transcoding preset group name
     *
     * @return the media transcoding preset group name
     */
    public String getTranscodingPresetGroupName() {
        return transcodingPresetGroupName;
    }

    /**
     * set media transcoding preset group name
     *
     * @param transcodingPresetGroupName the media transcoding preset group name
     */
    public void setTranscodingPresetGroupName(String transcodingPresetGroupName) {
        this.transcodingPresetGroupName = transcodingPresetGroupName;
    }

    /**
     * set media transcoding preset group name
     *
     * @param transcodingPresetGroupName the media transcoding preset group name
     * @return InternalCreateMediaRequest with media transcoding preset group name
     */
    public InternalCreateMediaRequest withTranscodingPresetGroupName(String transcodingPresetGroupName) {
        this.transcodingPresetGroupName = transcodingPresetGroupName;
        return this;
    }

    /**
     * get media description
     *
     * @return the media description
     */
    public String getDescription() {
        return description;
    }

    /**
     * set media description
     *
     * @param description the media description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * set media description
     *
     * @param description the media description
     * @return InternalCreateMediaRequest with media description
     */
    public InternalCreateMediaRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * get media source extension
     *
     * @return the media source extension
     */
    public String getSourceExtension() {
        return sourceExtension;
    }

    /**
     * set media source extension
     *
     * @param sourceExtension the media source extension
     */
    public void setSourceExtension(String sourceExtension) {
        this.sourceExtension = sourceExtension;
    }

    /**
     * set media source extension
     *
     * @param sourceExtension the media source extension
     * @return InternalCreateMediaRequest with media source extension
     */
    public InternalCreateMediaRequest withSourceExtension(String sourceExtension) {
        this.sourceExtension = sourceExtension;
        return this;
    }

    /**
     * get media transcoding priority
     *
     * @return the media transcoding priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * set media transcoding priority
     *
     * @param priority the media transcoding priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * set media transcoding priority
     *
     * @param priority the media transcoding priority
     * @return InternalCreateMediaRequest with media transcoding priority
     */
    public InternalCreateMediaRequest withPriority(int priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        setRequestCredentials(credentials);
        return this;
    }

}
