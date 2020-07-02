/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.vehicle;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

/**
 *  Response for query multiple shadow
 */
public class QueryMultipleShadowResponse extends AbstractBceResponse {
    private List<JsonNode> data;

    public List<JsonNode> getData() {
        return data;
    }

    public void setData(List<JsonNode> data) {
        this.data = data;
    }
}
