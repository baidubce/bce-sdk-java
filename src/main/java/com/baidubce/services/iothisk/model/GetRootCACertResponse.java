package com.baidubce.services.iothisk.model;

/**
 * Represent the response of get root cert.
 */
public class GetRootCACertResponse extends IotPkiManageResponse {

    /**
     * Crl download url
     */
    private String crl;

    /**
     * Root cert download url
     */
    private String downloadUrl;

    public String getCrl() {
        return crl;
    }

    public void setCrl(String crl) {
        this.crl = crl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}