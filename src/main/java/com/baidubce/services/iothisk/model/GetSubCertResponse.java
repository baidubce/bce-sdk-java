package com.baidubce.services.iothisk.model;

/**
 * Represent the response of get sub cert.
 */
public class GetSubCertResponse extends IotPkiManageResponse {

    /**
     * Root cert ID of the sub cert.
     */
    private String rootCertId;

    /**
     * Cert group ID of the sub cert.
     */
    private String groupId;

    /**
     * Download url of the sub cert.
     */
    private String downloadUrl;

    public String getRootCertId() {
        return rootCertId;
    }

    public void setRootCertId(String rootCertId) {
        this.rootCertId = rootCertId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
