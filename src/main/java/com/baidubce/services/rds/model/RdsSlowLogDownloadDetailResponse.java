package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * The response of rds slow log download detail
 */
public class RdsSlowLogDownloadDetailResponse extends AbstractBceResponse {

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
