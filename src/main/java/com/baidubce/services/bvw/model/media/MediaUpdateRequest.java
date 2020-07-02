/*
 * Copyright 2019 Baidu, Inc.
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

/**
 * Update media request.
 */
public class MediaUpdateRequest extends AbstractBceRequest {

    /**
     * The media id.
     */
    private String mediaId;
    /**
     * The media title.
     */
    private String title;
    /**
     * The media description.
     */
    private String description;

    /**
     * Construct a updating media request with specified parameters.
     *
     * @param mediaId     The media id
     * @param title       The media title
     * @param description The media description
     * @return A updating media request
     */
    public static MediaUpdateRequest of(String mediaId, String title, String description) {
        MediaUpdateRequest updateRequest = new MediaUpdateRequest();
        updateRequest.setMediaId(mediaId);
        updateRequest.setTitle(title);
        updateRequest.setDescription(description);
        return updateRequest;
    }

    @Override
    public MediaUpdateRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
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

}
