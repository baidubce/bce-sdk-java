/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.subnet.model;

import com.baidubce.model.AbstractBceResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateIpReservedResponse extends AbstractBceResponse {
    private String ipReserveId;

    @Override
    public String toString() {
        return "IpReservedId{" +
                "ipReserveId='" + ipReserveId + '\'' +
                '}';
    }
}
