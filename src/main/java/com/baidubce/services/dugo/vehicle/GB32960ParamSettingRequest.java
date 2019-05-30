/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.vehicle;

import com.baidubce.services.dugo.AbstractDuGoRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Request for setting params
 */
public class GB32960ParamSettingRequest extends AbstractDuGoRequest {
    public String vin;
    public String iccid;
    private Map<Integer, Object> params = new HashMap();

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public Map<Integer, Object> getParams() {
        return params;
    }

    public void setParams(Map<Integer, Object> params) {
        this.params = params;
    }
}
