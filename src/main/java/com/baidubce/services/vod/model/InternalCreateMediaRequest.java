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

    private String transcodingPresetGroupName;

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

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public InternalCreateMediaRequest withMediaId(String mediaId) {
        this.mediaId = mediaId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public InternalCreateMediaRequest withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTranscodingPresetGroupName() {
        return transcodingPresetGroupName;
    }

    public void setTranscodingPresetGroupName(String transcodingPresetGroupName) {
        this.transcodingPresetGroupName = transcodingPresetGroupName;
    }

    public InternalCreateMediaRequest withTranscodingPresetGroupName(String transcodingPresetGroupName) {
        this.transcodingPresetGroupName = transcodingPresetGroupName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InternalCreateMediaRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSourceExtension() {
        return sourceExtension;
    }

    public void setSourceExtension(String sourceExtension) {
        this.sourceExtension = sourceExtension;
    }

    public InternalCreateMediaRequest withSourceExtension(String sourceExtension) {
        this.sourceExtension = sourceExtension;
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        setRequestCredentials(credentials);
        return this;
    }

}
