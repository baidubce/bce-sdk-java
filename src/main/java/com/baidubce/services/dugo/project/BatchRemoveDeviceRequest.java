/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.project;

import com.baidubce.services.dugo.AbstractDuGoRequest;

import java.util.List;

/**
 * 批量将设备从用户账号下移除的请求
 */
public class BatchRemoveDeviceRequest extends AbstractDuGoRequest {
    private List<String> deviceIds;

    public BatchRemoveDeviceRequest(List<String> deviceIds) {
        this.deviceIds = deviceIds;
    }

    public List<String> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<String> deviceIds) {
        this.deviceIds = deviceIds;
    }
}
