/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.vehicle;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

/**
 * Response for shadow filter
 */
public class ShadowFilterResponse extends AbstractBceResponse {
    private long amount;
    private int pageNo;
    private int pageSize;
    private List<JsonNode> data;

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

    public List<JsonNode> getData() {
        return data;
    }

    public void setData(List<JsonNode> data) {
        this.data = data;
    }
}
