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

import com.baidubce.model.AbstractBceResponse;

/**
 * Contains the data returned by Baidu VOD from the <code>putObject</code> operation. Use this request to access
 * information about the new object created from the <code>putObject</code> request, such as its ETag and optional
 * version ID.
 */
public class GenerateMediaDeliveryInfoResponse extends AbstractBceResponse {

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

    /*
     * The playable file address.
     */
    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    /*
     * The media cover image address.
     */
    private String cover;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    /*
     * if user called publishsource interface, then this indicate download address.
     */
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private int duration;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GenerateMediaDeliveryInfo { \n");
        sb.append("  mediaId = ").append(mediaId).append("\n");
        sb.append("  file = ").append(file).append("\n");
        sb.append("  cover = ").append(cover).append("\n");
        sb.append("  title = ").append(title).append("\n");
        sb.append("  duration = ").append(duration).append("\n");
        if (source != null) {
            sb.append("  source = ").append(source).append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

}