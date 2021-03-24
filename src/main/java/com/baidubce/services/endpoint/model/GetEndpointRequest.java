/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.endpoint.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by XingChunyang
 * Date: 2021/01/21.
 */

public class GetEndpointRequest extends AbstractBceRequest {
    /**
     * The endpointId of endpoint.
     */
    private String endpointId;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Configure id for the request.
     *
     * @param endpointId The endpointId of GetEndpointRequest
     * @return GetEndpointRequest with specific name
     */
    public GetEndpointRequest withEndpointId(String endpointId) {
        this.endpointId = endpointId;
        return this;
    }

    public String getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(String endpointId) {
        this.endpointId = endpointId;
    }
}
