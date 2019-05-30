/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.project;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

/**
 * Response for querying device history
 */
public class QueryDeviceHistoryResponse extends AbstractBceResponse {
    private boolean truncated;
    private String marker;
    private List<IvcTsdbDataNode> dataList;

    public boolean isTruncated() {
        return truncated;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public List<IvcTsdbDataNode> getDataList() {
        return dataList;
    }

    public void setDataList(List<IvcTsdbDataNode> dataList) {
        this.dataList = dataList;
    }

    public static class IvcTsdbDataNode {
        private long timestamp;
        private String vehicleId;
        private JsonNode data;

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public JsonNode getData() {
            return data;
        }

        public void setData(JsonNode data) {
            this.data = data;
        }
    }
}
