package com.baidubce.services.vod.v2.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

public class MediaListRequest extends AbstractBceRequest {

    private String marker;
    private int maxSize = 10;
    private String name;
    private String mediaId;
    private String banStatus;
    private String sourceType;
    private List<String> mediaTypes;
    private List<String> categoryIds;
    private List<String> tags;
    private String beginTime;
    private String endTime;

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getBanStatus() {
        return banStatus;
    }

    public void setBanStatus(String banStatus) {
        this.banStatus = banStatus;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public List<String> getMediaTypes() {
        return mediaTypes;
    }

    public void setMediaTypes(List<String> mediaTypes) {
        this.mediaTypes = mediaTypes;
    }

    public List<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public MediaListRequest withMarker(String marker) {
        this.marker = marker;
        return this;
    }

    public MediaListRequest withMaxSize(int maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    public MediaListRequest withName(String name) {
        this.name = name;
        return this;
    }

    public MediaListRequest withMediaId(String mediaId) {
        this.mediaId = mediaId;
        return this;
    }

    public MediaListRequest withBanStatus(String banStatus) {
        this.banStatus = banStatus;
        return this;
    }

    public MediaListRequest withSourceType(String sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    public MediaListRequest withMediaTypes(List<String> mediaTypes) {
        this.mediaTypes = mediaTypes;
        return this;
    }

    public MediaListRequest withCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
        return this;
    }

    public MediaListRequest withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public MediaListRequest withBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public MediaListRequest withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

}
