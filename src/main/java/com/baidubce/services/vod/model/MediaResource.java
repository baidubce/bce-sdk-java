/*
 * Copyright 2016 Baidu, Inc.
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

import java.util.ArrayList;
import java.util.List;

public class MediaResource {

    private String mediaId;
    private String status;
    private Attributes attributes;
    private MediaMeta meta;
    private String publishTime;
    private String createTime;

    private String transcodingPresetGroupName;

    private String source;

    private List<PlayableUrl> playableUrlList = new ArrayList<PlayableUrl>();

    private List<String> thumbnailList = new ArrayList<String>();

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MediaMeta getMeta() {
        return meta;
    }

    public void setMeta(MediaMeta meta) {
        this.meta = meta;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public String getTranscodingPresetGroupName() {
        return transcodingPresetGroupName;
    }

    public void setTranscodingPresetGroupName(String transcodingPresetGroupName) {
        this.transcodingPresetGroupName = transcodingPresetGroupName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<PlayableUrl> getPlayableUrlList() {
        return playableUrlList;
    }

    public void setPlayableUrlList(List<PlayableUrl> playableUrlList) {
        this.playableUrlList = playableUrlList;
    }

    public List<String> getThumbnailList() {
        return thumbnailList;
    }

    public void setThumbnailList(List<String> thumbnailList) {
        this.thumbnailList = thumbnailList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("  MediaResource { \n");
        sb.append("    mediaId = ").append(mediaId).append("\n");
        sb.append("    status = ").append(status).append("\n");
        sb.append("    attributes = ").append(attributes).append("\n");
        sb.append("    meta = ").append(meta).append("\n");
        sb.append("    createTime = ").append(createTime).append("\n");
        sb.append("    publishTime = ").append(publishTime).append("\n");
        sb.append("  transcodingPresetGroupName = ").append(transcodingPresetGroupName).append("\n");
        sb.append("  source = ").append(source).append("\n");
        sb.append("  playableUrlList = [").append("\n");
        for (PlayableUrl url : playableUrlList) {
            sb.append(url.toString()).append("\n");
        }
        sb.append("] \n");
        sb.append("  thumbnailList = [").append("\n");
        for (String thumbnail : thumbnailList) {
            sb.append("    thumbnail =").append(thumbnail).append("\n");
        }
        sb.append("  }\n");
        return sb.toString();
    }

}
