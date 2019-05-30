package com.baidubce.services.iothisk.model;

import java.util.List;

/**
 * Represent the request for create sub cert.
 */
public class CreateSubCertRequest extends IotPkiManageRequest {

    /**
     * Cert group ID of the cert which will be created
     */
    private String groupId;

    /**
     * Device ID of the cert which will be created
     */
    private String deviceId;

    /**
     * Validity period of sub cert in days.
     */
    private int duration;

    /**
     * Csr file of the cert which will be created, encoded by base64, using PEM format.
     */
    private String csr;

    /**
     * Address list of the cert which will be created, only server cert can set it
     */
    private List<String> address;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public CreateSubCertRequest withGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public CreateSubCertRequest withDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public CreateSubCertRequest withDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public String getCsr() {
        return csr;
    }

    public void setCsr(String csr) {
        this.csr = csr;
    }

    public CreateSubCertRequest withCsr(String csr) {
        this.csr = csr;
        return this;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public CreateSubCertRequest withAddress(List<String> address) {
        this.address = address;
        return this;
    }
}
