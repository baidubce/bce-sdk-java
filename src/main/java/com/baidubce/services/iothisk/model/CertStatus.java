package com.baidubce.services.iothisk.model;

/**
 * Possible status of get cert status.
 */
public enum CertStatus {
    NotRevoke(0),
    Revoked(1),
    CertNotFound(2),
    CANotFount(3),
    UnknownError(4);

    private int statusCode;

    CertStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
