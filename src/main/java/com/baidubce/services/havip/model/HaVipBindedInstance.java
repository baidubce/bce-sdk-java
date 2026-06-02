/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.havip.model;

import lombok.Getter;
import lombok.Setter;

/**
 * HaVip绑定的实例信息
 */
@Getter
@Setter
public class HaVipBindedInstance {
    /**
     * 绑定的实例ID
     */
    private String instanceId;

    /**
     * 绑定的实例类型，"SERVER"表示云服务器（BCC/BBC/DCC），"ENI"表示弹性网卡
     */
    private String instanceType;

    /**
     * 是否为主实例
     */
    private Boolean master;

    @Override
    public String toString() {
        return "HaVipBindedInstance{" +
                "instanceId='" + instanceId + '\'' +
                ", instanceType='" + instanceType + '\'' +
                ", master=" + master +
                '}';
    }
}
