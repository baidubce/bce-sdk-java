package com.baidubce.services.cdn.model.stat;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.ArrayList;
import java.util.List;

public class GetUploadStatResponse extends CdnResponse {

    private List<UploadDetail> details = new ArrayList<UploadDetail>();

    private Long count;

    public GetUploadStatResponse() {
    }

    public List<UploadDetail> getDetails() {
        return details;
    }

    public void setDetails(List<UploadDetail> details) {
        this.details = details;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
