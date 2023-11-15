package com.baidubce.services.cdn.model.util;

import com.baidubce.services.cdn.model.CdnResponse;

public class GetForbiddenQuota extends CdnResponse {

    /**
     * url封禁总容量
     */
    private Long quota;

    /**
     * 当前封禁的url数目
     */
    private Long count;

    public GetForbiddenQuota() {
    }

    public Long getQuota() {
        return quota;
    }

    public void setQuota(Long quota) {
        this.quota = quota;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
