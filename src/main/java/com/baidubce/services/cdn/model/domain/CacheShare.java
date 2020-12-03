package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

public class CacheShare extends JsonObject {
    private boolean enabled;
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
