package com.baidubce.services.iothisk.model;

/**
 * Represent the response of create root cert.
 */
public class CreateRootCACertResponse extends IotPkiManageResponse {

    /**
     * Cert ID of the root cert.
     */
    private String certId;

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }
}
