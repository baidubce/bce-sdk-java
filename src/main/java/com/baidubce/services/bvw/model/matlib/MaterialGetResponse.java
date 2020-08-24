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
package com.baidubce.services.bvw.model.matlib;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * The response of getting material from material library.
 */
public class MaterialGetResponse extends AbstractBceResponse {

    /**
     * The material id
     */
    private String id;
    /**
     * The user id
     */
    private String userId;
    /**
     * The info type of material, only support ENTERTAINMENT now.
     */
    private String infoType;
    /**
     * The media type, contains video/audio/image.
     */
    private String mediaType;
    /**
     * The media source type, contains USER/SERVICE.
     */
    private String sourceType;
    /**
     * The statue of processing media source, contains PROCESSING/FAILED/FINISHED.
     */
    private String status;
    /**
     * The material title.
     */
    private String title;
    /**
     * The media source url.
     */
    private String sourceUrl;
    /**
     * The 360p preview url of media source.
     */
    private String sourceUrl360p;
    /**
     * The thumbnails preview urls of media source.
     */
    private List<String> thumbnailUrls;
    /**
     * The subtitles preview urls of media source.
     */
    private List<String> subtitleUrls;
    /**
     * The create time of this material.
     */
    private String createTime;
    /**
     * The update time of this material.
     */
    private String updateTime;
    /**
     * The video or audio duration of media source.
     */
    private Double duration;
    /**
     * The video of image height of media source.
     */
    private Integer height;
    /**
     * The video of image width of media source.
     */
    private Integer width;
    /**
     * The bos bucket to storage media source and process result.
     */
    private String bucket;
    /**
     * The bos key of media source.
     */
    private String key;
    /**
     * The bos key of 360p result.
     */
    private String key360p;
    /**
     * The bos key of 720p result.
     */
    private String key720p;
    /**
     * The bos key of audio result.
     */
    private String audioKey;
    /**
     * The bos keys of thumbnail results.
     */
    private List<String> thumbnailKeys;
    /**
     * The bos key of subtitle results.
     */
    private List<String> subtitles;

    public MaterialGetResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSourceUrl360p() {
        return sourceUrl360p;
    }

    public void setSourceUrl360p(String sourceUrl360p) {
        this.sourceUrl360p = sourceUrl360p;
    }

    public List<String> getThumbnailUrls() {
        return thumbnailUrls;
    }

    public void setThumbnailUrls(List<String> thumbnailUrls) {
        this.thumbnailUrls = thumbnailUrls;
    }

    public List<String> getSubtitleUrls() {
        return subtitleUrls;
    }

    public void setSubtitleUrls(List<String> subtitleUrls) {
        this.subtitleUrls = subtitleUrls;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey360p() {
        return key360p;
    }

    public void setKey360p(String key360p) {
        this.key360p = key360p;
    }

    public String getKey720p() {
        return key720p;
    }

    public void setKey720p(String key720p) {
        this.key720p = key720p;
    }

    public String getAudioKey() {
        return audioKey;
    }

    public void setAudioKey(String audioKey) {
        this.audioKey = audioKey;
    }

    public List<String> getThumbnailKeys() {
        return thumbnailKeys;
    }

    public void setThumbnailKeys(List<String> thumbnailKeys) {
        this.thumbnailKeys = thumbnailKeys;
    }

    public List<String> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(List<String> subtitles) {
        this.subtitles = subtitles;
    }

    @Override
    public String toString() {
        return "MaterialGetResponse{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", infoType='" + infoType + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", sourceUrl360p='" + sourceUrl360p + '\'' +
                ", thumbnailUrls=" + thumbnailUrls +
                ", subtitleUrls=" + subtitleUrls +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", duration=" + duration +
                ", height=" + height +
                ", width=" + width +
                ", bucket='" + bucket + '\'' +
                ", key='" + key + '\'' +
                ", key360p='" + key360p + '\'' +
                ", key720p='" + key720p + '\'' +
                ", audioKey='" + audioKey + '\'' +
                ", thumbnailKeys=" + thumbnailKeys +
                ", subtitles=" + subtitles +
                '}';
    }

}
