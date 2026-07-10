package com.baidubce.services.eo.model.cache;

import com.baidubce.services.eo.model.JsonObject;

/**
 * cache purge task.
 */
public class PurgeTask extends JsonObject {
    /**
     * Purge file type.
     */
    public static final String TYPE_FILE = "file";

    /**
     * Purge directory type.
     */
    public static final String TYPE_DIRECTORY = "directory";

    /**
     * The url to purge.
     */
    private String url;

    /**
     * The purge type.
     */
    private String type;

    /**
     * @param url
     * @return this object
     */
    public PurgeTask withUrl(String url) {
        this.url = url;
        this.type = TYPE_FILE;
        return this;
    }

    /**
     * @param url
     * @return this object
     */
    public PurgeTask withDirectory(String url) {
        this.url = url;
        this.type = TYPE_DIRECTORY;
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

    /**
     * @param type
     * @return this object
     */
    public PurgeTask withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}
