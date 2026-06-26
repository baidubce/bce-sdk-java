package com.baidubce.services.scs.model.backup;

import com.baidubce.model.AbstractBceResponse;

/**
 * Response of scs backup detail.
 */
public class ScsBackupDetailResponse extends AbstractBceResponse {

    private String url;
    private Integer urlExpiration;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUrlExpiration() {
        return urlExpiration;
    }

    public void setUrlExpiration(Integer urlExpiration) {
        this.urlExpiration = urlExpiration;
    }
}
