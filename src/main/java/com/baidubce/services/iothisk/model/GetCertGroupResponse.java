package com.baidubce.services.iothisk.model;

import java.util.List;

/**
 * Represent the response of get cert group.
 */
public class GetCertGroupResponse extends IotPkiManageResponse {

    /**
     * Root cert ID of the group.
     */
    private String rootCertId;

    /**
     * Server cert ID list of the group.
     */
    private List<String> serverCerts;

    /**
     * Client cert ID list of the group.
     */
    private List<String> clientCerts;

    public String getRootCertId() {
        return rootCertId;
    }

    public void setRootCertId(String rootCertId) {
        this.rootCertId = rootCertId;
    }

    public List<String> getServerCerts() {
        return serverCerts;
    }

    public void setServerCerts(List<String> serverCerts) {
        this.serverCerts = serverCerts;
    }

    public List<String> getClientCerts() {
        return clientCerts;
    }

    public void setClientCerts(List<String> clientCerts) {
        this.clientCerts = clientCerts;
    }

}