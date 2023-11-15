package com.baidubce.services.cdn.model.certificate;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.List;

public class GetHttpsDomainResponse extends CdnResponse {

    private long count;

    private List<HttpsDomain> rows;

    public GetHttpsDomainResponse() {
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<HttpsDomain> getRows() {
        return rows;
    }

    public void setRows(List<HttpsDomain> rows) {
        this.rows = rows;
    }
}
