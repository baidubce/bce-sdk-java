package com.baidubce.services.bos.model;

import java.util.Date;

import java.util.Map;

public class BosPrefixInfo {
    protected String prefix;
    protected Date lastModified = null;

    protected Map<String, String> userMeta;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Map<String, String> getUserMeta() {
        return userMeta;
    }

    public void setUserMeta(Map<String, String> userMeta) {
        this.userMeta = userMeta;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
