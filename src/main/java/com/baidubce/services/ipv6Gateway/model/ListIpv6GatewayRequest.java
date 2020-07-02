/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ipv6Gateway.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.ListRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by wangkai on 2019/8/16
 */
public class ListIpv6GatewayRequest extends ListRequest {

    @JsonIgnore
    private String vpcId;

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    @Override
    public ListIpv6GatewayRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public ListIpv6GatewayRequest withRequestVpcId(String vpcId) {
        this.setVpcId(vpcId);
        return this;
    }

}
