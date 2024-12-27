package com.baidubce.services.vod.v2.model;

import java.util.Date;
import java.util.List;

public class MediaBasicInfo {

    private String mediaId;
    private String name;
    private String description;
    private String mediaType;
    private String banStatus;
    private Date createTime;
    private String categoryId;
    private List<String> tags;
    private MediaSourceInfo source;
    private MediaMetadataInfo sourceMetadata;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getBanStatus() {
        return banStatus;
    }

    public void setBanStatus(String banStatus) {
        this.banStatus = banStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public MediaSourceInfo getSource() {
        return source;
    }

    public void setSource(MediaSourceInfo source) {
        this.source = source;
    }

    public MediaMetadataInfo getSourceMetadata() {
        return sourceMetadata;
    }

    public void setSourceMetadata(MediaMetadataInfo sourceMetadata) {
        this.sourceMetadata = sourceMetadata;
    }

}
