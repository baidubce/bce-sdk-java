package com.baidubce.services.iothisk.model;

/**
 * Represent the response of renew sub cert.
 */
public class RenewSubCertResponse extends IotPkiManageResponse {

    /**
     * Download url of the sub cert.
     */
    private String downloadUrl;

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
