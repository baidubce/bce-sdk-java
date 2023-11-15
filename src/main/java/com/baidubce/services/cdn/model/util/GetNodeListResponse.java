package com.baidubce.services.cdn.model.util;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.List;

public class GetNodeListResponse extends CdnResponse {
    private int status;
    private List<NodeDetail> details;

    public GetNodeListResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<NodeDetail> getDetails() {
        return details;
    }

    public void setDetails(List<NodeDetail> details) {
        this.details = details;
    }
}
