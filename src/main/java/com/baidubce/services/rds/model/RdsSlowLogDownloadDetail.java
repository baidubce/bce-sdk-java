package com.baidubce.services.rds.model;

/**
 * The download detail of specific slow log
 */
public class RdsSlowLogDownloadDetail {
    private String url;
    private String downloadExpires;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDownloadExpires() {
        return downloadExpires;
    }

    public void setDownloadExpires(String downloadExpires) {
        this.downloadExpires = downloadExpires;
    }
}
