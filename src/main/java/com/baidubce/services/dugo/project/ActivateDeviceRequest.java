/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.project;

import com.baidubce.services.dugo.AbstractDuGoRequest;

import java.util.List;

/**
 * 批量激活设备请求
 */
public class ActivateDeviceRequest extends AbstractDuGoRequest {
    private List<String> deviceIds;

    public ActivateDeviceRequest(List<String> deviceIds) {
        this.deviceIds = deviceIds;
    }

    public List<String> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<String> deviceIds) {
        this.deviceIds = deviceIds;
    }
}
