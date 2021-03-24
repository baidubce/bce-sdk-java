/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.endpoint.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * Created by XingChunyang
 * Date: 2021/01/21.
 */

public class CreateEndpointResponse extends AbstractBceResponse {
    /**
     * endpointId
     */
    private String id;
    /**
     * endpoint's ip
     */
    private String ipAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
