/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.project;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Response for query device shadow
 */
public class DeviceShadowResponse extends AbstractBceResponse {
    private JsonNode data;
    private JsonNode lastUpdatedTime;

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public JsonNode getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(JsonNode lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
