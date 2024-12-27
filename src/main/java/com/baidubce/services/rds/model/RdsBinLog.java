package com.baidubce.services.rds.model;

import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsBinLog {
    private String downloadUrl;
    private String downloadExpires;

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDownloadExpires() {
        return downloadExpires;
    }

    public void setDownloadExpires(String downloadExpires) {
        this.downloadExpires = downloadExpires;
    }
}

