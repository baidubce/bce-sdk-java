package com.baidubce.services.vod.v2.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;


public class MediaUploadResponse extends AbstractBceResponse {
    private List<String> urls;
    private String sessionKey;

    public MediaUploadResponse() {
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public static MediaUploadResponse of(List<String> urls, String sessionKey) {
        MediaUploadResponse mediaUploadResponse = new MediaUploadResponse();
        mediaUploadResponse.setUrls(urls);
        mediaUploadResponse.setSessionKey(sessionKey);
        return mediaUploadResponse;
    }

}
