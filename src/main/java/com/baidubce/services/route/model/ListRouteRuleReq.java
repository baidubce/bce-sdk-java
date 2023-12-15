/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.route.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.ListRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListRouteRuleReq extends ListRequest {

    private String routeTableId;
    private String vpcId;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
