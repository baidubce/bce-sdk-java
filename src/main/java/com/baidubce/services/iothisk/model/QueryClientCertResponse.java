package com.baidubce.services.iothisk.model;

import java.util.List;

/**
 * Represent the response of query client certs.
 */
public class QueryClientCertResponse extends IotPkiManageResponse {

    /**
     * Client cert list.
     */
    private List<String> clientCerts;

    public List<String> getClientCerts() {
        return clientCerts;
    }

    public void setClientCerts(List<String> clientCerts) {
        this.clientCerts = clientCerts;
    }
}
