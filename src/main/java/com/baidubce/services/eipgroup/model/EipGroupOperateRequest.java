/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eipgroup.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * The request for operate eipgroup.
 */
@Getter
@Setter
public class EipGroupOperateRequest extends AbstractBceRequest {

    /**
     * An ASCII string whose length is less than 64.
     *
     * The request will be idempotent if clientToken is provided.
     * If the clientToken is not specified by the user, a random String generated by default algorithm will be used.
     * See more detail at
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#.E5.B9.82.E7.AD.89.E6.80.A7">
     *     BCE API doc</a>
     */
    @JsonIgnore
    private String clientToken;

    /**
     * The id of eip group.
     */
    private String id;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Configure id for the request.
     *
     * @param id The id of EipNameRequest
     * @return EipNameRequest with specific id
     */
    public EipGroupOperateRequest withId(String id) {
        this.id = id;
        return this;
    }
}