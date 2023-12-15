/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.havip.model;

import com.baidubce.model.AbstractBceResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HaVipResponse extends AbstractBceResponse {
    private String haVipId;
    private String name;
    private String description;
    private String vpcId;
    private String subnetId;
    private String status;
    private String privateIpAddress;
    private String publicIpAddress;
    private String createdTime;

    @Override
    public String toString() {
        return "HaVip{" +
                "haVipId='" + haVipId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", vpcId='" + vpcId + '\'' +
                ", subnetId='" + subnetId + '\'' +
                ", status='" + status + '\'' +
                ", privateIpAddress='" + privateIpAddress + '\'' +
                ", publicIpAddress='" + publicIpAddress + '\'' +
                ", createdTime='" + createdTime + '\'' +
                '}';
    }
}
