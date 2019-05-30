package com.baidubce.services.iothisk.model;

/**
 * The base class of pki response which contains binary data.
 */
public abstract class IotPkiManageDataResponse extends IotPkiManageResponse {

    protected byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
