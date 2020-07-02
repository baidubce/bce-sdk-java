/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eipbp.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for get eipBp detail.
 */
public class GetEipBpRequest extends AbstractBceRequest {

    /**
     * EipBp id.
     * */
    private String id;

    /**
     * Configure id for the request.
     *
     * @param id The id of GetEipBpRequest
     * @return GetEipBpRequest with specific id
     */
    public GetEipBpRequest withId(String id) {
        this.id = id;
        return this;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GetEipBpRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
