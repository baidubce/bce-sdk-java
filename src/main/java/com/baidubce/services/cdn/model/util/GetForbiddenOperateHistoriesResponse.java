package com.baidubce.services.cdn.model.util;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.List;

public class GetForbiddenOperateHistoriesResponse extends CdnResponse {
    private long count;
    private List<UrlRecord> urlHistories;

    public GetForbiddenOperateHistoriesResponse() {
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<UrlRecord> getUrlHistories() {
        return urlHistories;
    }

    public void setUrlHistories(List<UrlRecord> urlHistories) {
        this.urlHistories = urlHistories;
    }
}
