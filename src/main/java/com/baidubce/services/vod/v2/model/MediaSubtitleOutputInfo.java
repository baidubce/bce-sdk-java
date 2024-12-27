package com.baidubce.services.vod.v2.model;

import java.util.List;

public class MediaSubtitleOutputInfo {

    private String id;
    private List<String> urls;
    private List<String> formats;

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

    public List<String> getFormats() {
        return formats;
    }

    public void setFormats(List<String> formats) {
        this.formats = formats;
    }

}
