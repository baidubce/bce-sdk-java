/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.havip.model;

import java.util.List;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BindInstanceRequest extends AbstractBceRequest {
    /**
     * The param havipId is you will operate to bind instance
     */
    private String haVipId;
    /**
     * The instanceIds is A list that you will bind to the HaVip
     */
    private List<String> instanceIds;
    /**
     * The instanceType is Type of instance you will bind ,like  BCC/BBC/ENI
     */
    private String instanceType;

    @JsonIgnore
    private String clientToken;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
