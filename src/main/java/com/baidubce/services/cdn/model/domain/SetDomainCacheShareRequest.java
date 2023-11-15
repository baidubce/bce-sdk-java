package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * cache share between domains
 */
public class SetDomainCacheShareRequest extends CdnRequest {
    private String domain;
    private CacheShare cacheShare;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @param domain the domain name
     * @return returns this object
     */
    public SetDomainCacheShareRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public CacheShare getCacheShare() {
        return cacheShare;
    }

    public void setCacheShare(CacheShare cacheShare) {
        this.cacheShare = cacheShare;
    }



    public SetDomainCacheShareRequest withCacheShare(CacheShare cacheShare) {
        setCacheShare(cacheShare);
        return this;
    }

}
