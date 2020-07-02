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
 * Base media request.
 */
public class MediaBaseRequest extends AbstractBceRequest {

    /**
     * The media id.
     */
    private String mediaId;

    /**
     * Construct a base media request with specified media id.
     *
     * @param mediaId The media id
     * @return A base media request
     */
    public static MediaBaseRequest of(String mediaId) {
        MediaBaseRequest baseRequest = new MediaBaseRequest();
        baseRequest.setMediaId(mediaId);
        return baseRequest;
    }

    @Override
    public MediaBaseRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
