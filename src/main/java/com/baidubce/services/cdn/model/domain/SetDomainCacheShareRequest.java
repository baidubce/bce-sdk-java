package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * cache share between domains
 */
public class SetDomainCacheShareRequest extends CdnRequest {

    public CacheShare getCacheShare() {
        return cacheShare;
    }

    public void setCacheShare(CacheShare cacheShare) {
        this.cacheShare = cacheShare;
    }

    private CacheShare cacheShare;

    public SetDomainCacheShareRequest withCacheShare(CacheShare cacheShare) {
        setCacheShare(cacheShare);
        return this;
    }

}
