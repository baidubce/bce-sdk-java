/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.project;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

/**
 * Response for querying a group of devices
 */
public class GroupDeviceShadowResponse extends AbstractBceResponse {
    private long amount;
    private int pageNo;
    private int pageSize;
    private List<DeviceShadow> list;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<DeviceShadow> getList() {
        return list;
    }

    public void setList(List<DeviceShadow> list) {
        this.list = list;
    }

    public static class DeviceShadow {
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
}
