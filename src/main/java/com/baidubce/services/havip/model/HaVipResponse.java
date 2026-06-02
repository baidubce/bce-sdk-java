/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.havip.model;

import java.util.List;

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
    /**
     * 高可用虚拟IP绑定的实例列表
     */
    private List<HaVipBindedInstance> bindedInstances;

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
                ", bindedInstances=" + bindedInstances +
                '}';
    }
}
