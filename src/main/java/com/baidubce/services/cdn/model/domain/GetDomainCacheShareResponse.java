package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * create by hansongda on 20/12/01
 */
public class GetDomainCacheShareResponse extends CdnResponse {
    private CacheShare cacheShare;

    public CacheShare getCacheShare() {
        return cacheShare;
    }

    public void setCacheShare(CacheShare cacheShare) {
        this.cacheShare = cacheShare;
    }
}
