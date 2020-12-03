package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

public class Hsts extends JsonObject {
    private int maxAge;
    private boolean includeSubDomains;

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public boolean isIncludeSubDomains() {
        return includeSubDomains;
    }

    public void setIncludeSubDomains(boolean includeSubDomains) {
        this.includeSubDomains = includeSubDomains;
    }

    public boolean isPreload() {
        return preload;
    }

    public void setPreload(boolean preload) {
        this.preload = preload;
    }

    private boolean preload;

    public Hsts withMaxAge(int maxAge) {
        setMaxAge(maxAge);
        return this;
    }

    public Hsts withIncludeSubDomains(boolean includeSubDomains) {
        setIncludeSubDomains(includeSubDomains);
        return this;
    }

    public Hsts withPreload(boolean preload) {
        setPreload(preload);
        return this;
    }


}
