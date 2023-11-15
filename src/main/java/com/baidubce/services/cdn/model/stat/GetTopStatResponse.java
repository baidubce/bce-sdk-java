package com.baidubce.services.cdn.model.stat;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.ArrayList;
import java.util.List;

public class GetTopStatResponse extends CdnResponse {
    /**
     * 正常返回的时候为"ok"
     */
    private String status;

    private List<TopStatDetail> details = new ArrayList<TopStatDetail>();

    private Long count;

    public GetTopStatResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TopStatDetail> getDetails() {
        return details;
    }

    public void setDetails(List<TopStatDetail> details) {
        this.details = details;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
