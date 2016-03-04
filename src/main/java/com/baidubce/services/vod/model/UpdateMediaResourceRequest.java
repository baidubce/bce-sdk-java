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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UpdateMediaResourceRequest extends AbstractBceRequest {

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UpdateMediaResourceRequest { \n");
        sb.append("   mediaId = ").append(mediaId).append("\n");
        sb.append("   title = ").append(title).append("\n");
        sb.append("   description = ").append(description).append("\n");
        sb.append("  }\n");
        return sb.toString();
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public UpdateMediaResourceRequest withMediaId(String mediaId) {
        this.mediaId = mediaId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UpdateMediaResourceRequest withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UpdateMediaResourceRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
