package com.baidubce.services.iothisk.model;

/**
 * Represent the response of download crl.
 */
public class DownloadCrlResponse extends IotPkiManageDataResponse {

    public String getCrlContent() {
        return new String(data);
    }

}
