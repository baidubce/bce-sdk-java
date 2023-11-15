package com.baidubce.services.cdn.model.util;

import com.baidubce.services.cdn.model.CdnRequest;

import java.util.List;

public class SetForbiddenUrlsRequest extends CdnRequest {
    private List<String> urls;

    public SetForbiddenUrlsRequest() {
    }

    public List<String> getUrls() {
        return urls;
    }

    public SetForbiddenUrlsRequest withUrls(List<String> urls) {
        this.urls = urls;
        return this;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
