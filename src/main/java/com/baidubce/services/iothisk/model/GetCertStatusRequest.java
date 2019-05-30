package com.baidubce.services.iothisk.model;

/**
 * Represent the request for get cert status.
 */
public class GetCertStatusRequest extends IotPkiManageRequest {

    /**
     * Issuer DN of the cert which will be queried, can be found in cert content.
     */
    private String issuerDN;

    /**
     * Certificate serial number of the cert which will be queried, can be found in cert content.
     * For example: 2c932aea1bc3dce9
     */
    private String certificateSN;

    public String getIssuerDN() {
        return issuerDN;
    }

    public void setIssuerDN(String issuerDN) {
        this.issuerDN = issuerDN;
    }

    public GetCertStatusRequest withIssuerDN(String issuerDN) {
        this.issuerDN = issuerDN;
        return this;
    }

    public String getCertificateSN() {
        return certificateSN;
    }

    public void setCertificateSN(String certificateSN) {
        this.certificateSN = certificateSN;
    }

    public GetCertStatusRequest withCertificateSN(String certificateSN) {
        this.certificateSN = certificateSN;
        return this;
    }
}
