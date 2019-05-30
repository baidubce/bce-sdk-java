package com.baidubce.services.iothisk.model;

/**
 * Represent the response of create sub cert.
 */
public class CreateSubCertResponse extends IotPkiManageResponse {

    /**
     * Cert ID of the sub cert.
     */
    private String certId;

    /**
     * Sub cert download url
     */
    private String downloadUrl;

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
