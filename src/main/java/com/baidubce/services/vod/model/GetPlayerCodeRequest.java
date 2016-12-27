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
package com.baidubce.services.vod.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class GetPlayerCodeRequest extends AbstractBceRequest {

    /*
     * Unique Id of media resource
     */
    private String mediaId;

    /*
     * The width of player view
     */
    private int width;

    /*
     * The height of player view
     */
    private int height;

    /*
     * Indicate whether to start automatically
     */
    private Boolean autoStart;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GetPlayerCodeRequest { \n");
        sb.append("  mediaId = ").append(mediaId).append("\n");
        sb.append("  width = ").append(width).append("\n");
        sb.append("  height = ").append(height).append("\n");
        sb.append("  autoStart = ").append(autoStart).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

    public String getMediaId() {
        return mediaId;
    }


    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public GetPlayerCodeRequest withMediaId(String mediaId) {
        this.mediaId = mediaId;
        return this;
    }


    public int getWidth() {
        return width;
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public GetPlayerCodeRequest withWidth(int width) {
        this.width = width;
        ;
        return this;
    }


    public int getHeight() {
        return height;
    }


    public void setHeight(int height) {
        this.height = height;
    }

    public GetPlayerCodeRequest withHeight(int height) {
        this.height = height;
        return this;
    }


    public Boolean isAutoStart() {
        return autoStart;
    }


    public void setAutoStart(Boolean autoStart) {
        this.autoStart = autoStart;
    }

    public GetPlayerCodeRequest withAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
