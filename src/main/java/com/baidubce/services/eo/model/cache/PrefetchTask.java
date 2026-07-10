package com.baidubce.services.eo.model.cache;

import com.baidubce.services.eo.model.JsonObject;

/**
 * cache prefetch task.
 */
public class PrefetchTask extends JsonObject {
    /**
     * The url to prefetch.
     */
    private String url;

    /**
     * @param url
     * @return this object
     */
    public PrefetchTask withUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
