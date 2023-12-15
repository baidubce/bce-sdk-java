/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.endpoint.model;

import java.util.List;

import com.baidubce.model.ListResponse;

/**
 * Created by XingChunyang
 * Date: 2021/01/21.
 */

public class ListEndpointResponse extends ListResponse {

    /**
     * The endpoint list
     */
    List<Endpoint> endpoints;

    public List<Endpoint> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<Endpoint> endpoints) {
        this.endpoints = endpoints;
    }

    @Override
    public String toString() {
        return "ListEndpointResponse{" +
                "marker= " + getMarker() + "," +
                "nextMarker= " + getNextMarker() + "," +
                "maxKeys= " + getMaxKeys() + "," +
                "isTruncated= " + getIsTruncated() + "," +
                "endpoints=" + endpoints +
                '}';
    }
}
