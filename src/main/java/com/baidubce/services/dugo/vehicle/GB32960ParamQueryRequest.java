/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.vehicle;

import com.baidubce.services.dugo.AbstractDuGoRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Request for querying GB/T 32960 vehicle parameters
 */
public class GB32960ParamQueryRequest extends AbstractDuGoRequest {
    public String vin;
    public String iccid;
    private List<Integer> paramIds = new ArrayList();

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

    public List<Integer> getParamIds() {
        return paramIds;
    }

    public void setParamIds(List<Integer> paramIds) {
        this.paramIds = paramIds;
    }
}
