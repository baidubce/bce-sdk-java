package com.baidubce.services.cdn.model.stat;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.ArrayList;
import java.util.List;

public class GetErrorCodeStatResponse extends CdnResponse {

    /**
     * 正常返回的时候为"ok"
     */
    private String status;

    private List<ErrorCodeStatDetail> details = new ArrayList<ErrorCodeStatDetail>();

    private Long count;

    public GetErrorCodeStatResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ErrorCodeStatDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ErrorCodeStatDetail> details) {
        this.details = details;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
