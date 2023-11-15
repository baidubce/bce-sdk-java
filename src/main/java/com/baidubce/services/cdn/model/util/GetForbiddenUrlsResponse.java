package com.baidubce.services.cdn.model.util;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.List;

public class GetForbiddenUrlsResponse extends CdnResponse {
    private long count;
    private List<UrlRecord> urlRecords;

    public GetForbiddenUrlsResponse() {
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<UrlRecord> getUrlRecords() {
        return urlRecords;
    }

    public void setUrlRecords(List<UrlRecord> urlRecords) {
        this.urlRecords = urlRecords;
    }
}
