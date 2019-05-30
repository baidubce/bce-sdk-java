/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.vehicle;

import com.baidubce.model.AbstractBceResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Response for param querying
 */
public class GB32960ParamQueryResponse extends AbstractBceResponse {
    private Map<Integer, Object> params = new HashMap<Integer, Object>();

    public Map<Integer, Object> getParams() {
        return params;
    }

    public void setParams(Map<Integer, Object> params) {
        this.params = params;
    }
}
