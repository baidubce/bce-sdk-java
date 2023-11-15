package com.baidubce.services.bvw.model.matlib;

import java.util.List;

/**
 * Material response.
 */
public class GetMaterialResponse {

    private String id;
    private String userId;
    private String actualUserId;
    private String saasType;
    private String infoType;
    private String mediaType;
    private String sourceType;
    private String status;
    private String title;
    private String sourceUrl;
    private String sourceUrl360p;
    private String audioUrl;
    private List<String> thumbnailList;
    private List<String> subtitleUrls;
    private String createTime;
    private String updateTime;
    private Double duration;
    private Integer height;
    private Integer width;
    private Long fileSizeInByte;
    private String bucket;
    private String key;
    private String key360p;
    private String key720p;

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

    public String getActualUserId() {
        return actualUserId;
    }

    public void setActualUserId(String actualUserId) {
        this.actualUserId = actualUserId;
    }

    public String getSaasType() {
        return saasType;
    }

    public void setSaasType(String saasType) {
        this.saasType = saasType;
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

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public List<String> getThumbnailList() {
        return thumbnailList;
    }

    public void setThumbnailList(List<String> thumbnailList) {
        this.thumbnailList = thumbnailList;
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

    public Long getFileSizeInByte() {
        return fileSizeInByte;
    }

    public void setFileSizeInByte(Long fileSizeInByte) {
        this.fileSizeInByte = fileSizeInByte;
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

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    private String audioKey;
    private List<String> thumbnailKeys;
    private List<String> subtitles;
    private String endpoint;

}
