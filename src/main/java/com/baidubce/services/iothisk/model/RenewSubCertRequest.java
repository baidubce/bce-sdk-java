package com.baidubce.services.iothisk.model;

import java.util.List;

/**
 * Represent the request for renew sub cert.
 */
public class RenewSubCertRequest extends IotPkiManageRequest {

    /**
     * New device ID of the cert which will be renewed
     */
    private String newDeviceId;

    /**
     * Validity period of sub cert in days.
     */
    private int duration;

    /**
     * Csr file of the cert which will be renewed, PEM format, encoded by base64
     */
    private String csr;

    /**
     * New address list of the cert which will be renewed, only server cert can set it
     */
    private List<String> newAddress;

    public String getNewDeviceId() {
        return newDeviceId;
    }

    public void setNewDeviceId(String newDeviceId) {
        this.newDeviceId = newDeviceId;
    }

    public RenewSubCertRequest withNewDeviceId(String newDeviceId) {
        this.newDeviceId = newDeviceId;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public RenewSubCertRequest withDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public String getCsr() {
        return csr;
    }

    public void setCsr(String csr) {
        this.csr = csr;
    }

    public RenewSubCertRequest withCsr(String csr) {
        this.csr = csr;
        return this;
    }

    public List<String> getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(List<String> newAddress) {
        this.newAddress = newAddress;
    }

    public RenewSubCertRequest withNewAddress(List<String> newAddress) {
        this.newAddress = newAddress;
        return this;
    }
}
