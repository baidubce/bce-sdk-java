package com.baidubce.services.cdn.model.stat;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.Map;

public class GetIpv6StatResponse extends CdnResponse {
    private Long count;
    private Map<String, Map<String, Ipv6Data>> details;

    public GetIpv6StatResponse() {
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Map<String, Map<String, Ipv6Data>> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Map<String, Ipv6Data>> details) {
        this.details = details;
    }
}
