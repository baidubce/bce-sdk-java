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

import com.baidubce.model.AbstractBceResponse;

/**
 * Contains the data returned by Baidu VOD from the <code>putObject</code> operation. Use this request to access
 * information about the new object created from the <code>putObject</code> request, such as its ETag and optional
 * version ID.
 */
public class CreateMediaResourceResponse extends AbstractBceResponse {

    /*
     * The unique ID of media resource managed by VOD service.
     */
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CreateMediaResourceResponse { \n");
        sb.append("  mediaId = ").append(mediaId).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}