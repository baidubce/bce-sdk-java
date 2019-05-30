package com.baidubce.services.iothisk.model;

import java.util.List;

/**
 * Represent the response of query server certs.
 */
public class QueryServerCertResponse extends IotPkiManageResponse {

    /**
     * Server cert list.
     */
    private List<String> serverCerts;

    public List<String> getServerCerts() {
        return serverCerts;
    }

    public void setServerCerts(List<String> serverCerts) {
        this.serverCerts = serverCerts;
    }

}
