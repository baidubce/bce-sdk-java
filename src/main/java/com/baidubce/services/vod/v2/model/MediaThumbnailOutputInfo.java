package com.baidubce.services.vod.v2.model;

import java.util.List;

public class MediaThumbnailOutputInfo {

    private String id;
    private List<String> urls;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

}
