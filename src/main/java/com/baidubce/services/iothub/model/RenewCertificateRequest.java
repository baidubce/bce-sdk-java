package com.baidubce.services.iothub.model;

/**
 * Represent the request to renew the certificate.
 */
public class RenewCertificateRequest extends QueryPrincipalRequest {


    public RenewCertificateRequest withPrincipalName(String principalName) {
        this.setPrincipalName(principalName);
        return this;
    }

    public RenewCertificateRequest withEndpointName(String endpointName) {
        this.setEndpointName(endpointName);
        return this;
    }


}
