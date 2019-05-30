package com.baidubce.services.iothisk.model;

/**
 * Represent the response of get ocsp response.
 */
public class GetOcspResponse extends IotPkiManageDataResponse {

    public byte[] getOcspResponse() {
        return data;
    }

}
