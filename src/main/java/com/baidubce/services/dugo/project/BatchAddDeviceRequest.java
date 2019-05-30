/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.project;

import com.baidubce.services.dugo.AbstractDuGoRequest;

import java.util.List;

/**
 * 批量将设备添加到用户账号下的请求
 */
public class BatchAddDeviceRequest extends AbstractDuGoRequest {
    private List<DeviceBindInfo> deviceBindInfoList;

    public BatchAddDeviceRequest(List<DeviceBindInfo> deviceBindInfoList) {
        this.deviceBindInfoList = deviceBindInfoList;
    }

    public List<DeviceBindInfo> getDeviceBindInfoList() {
        return deviceBindInfoList;
    }

    public void setDeviceBindInfoList(List<DeviceBindInfo> deviceBindInfoList) {
        this.deviceBindInfoList = deviceBindInfoList;
    }

    public static class DeviceBindInfo {
        private String pk;
        private String dn;
        private String sign;

        public DeviceBindInfo(String pk, String dn, String sign) {
            this.pk = pk;
            this.dn = dn;
            this.sign = sign;
        }

        public String getPk() {
            return pk;
        }

        public void setPk(String pk) {
            this.pk = pk;
        }

        public String getDn() {
            return dn;
        }

        public void setDn(String dn) {
            this.dn = dn;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
