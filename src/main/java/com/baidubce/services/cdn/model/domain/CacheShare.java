package com.baidubce.services.cdn.model.domain;

public class CacheShare {

    /**
     * 是否开启缓存共享
     * 必选
     */
    private boolean enabled;

    /**
     * 客户名下其他域名，开启情况下须传入该参数
     * 可选
     */
    private String domain;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public CacheShare withEnabled(boolean enabled) {
        setEnabled(enabled);
        return this;
    }

    public CacheShare withDomain(String domain) {
        setDomain(domain);
        return this;
    }
}
