/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cfw.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListCfwRequest {
    /**
     * 批量获取列表查询的起始位置，是一个由系统生成的字符串
     */
    private String marker;
    /**
     * 每页包含的最大数量，最大数量通常不超过1000，缺省值为1000
     */
    private Integer maxKeys;

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public Integer getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }
}
