/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.havip.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnBindEipRequest extends AbstractBceRequest {
    private String haVipId;

    @JsonIgnore
    private String clientToken;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
