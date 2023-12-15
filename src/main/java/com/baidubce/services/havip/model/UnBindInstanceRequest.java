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
public class UnBindInstanceRequest extends AbstractBceRequest {
    /**
     * The param haVipId is used to unbind instance
     */
    private String haVipId;
    /**
     * The instanceIds is already binded the haVipId,and you want to unbind
     */
    private List<String> instanceIds;
    /**
     * The instance's type ,like BCC/BBC/ENI..
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
